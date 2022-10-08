package androidx.appcompat.app

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewStub
import android.widget.*
import androidx.appcompat.widget.ContentFrameLayout
import androidx.appcompat.widget.FitWindowsViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.widget.ViewStubCompat
import androidx.collection.SimpleArrayMap
import com.harreke.easyapp.skin.apcompat.stainer.*
import com.harreke.easyapp.skin.core.ISkinStainerWrapper
import com.harreke.easyapp.skin.core.ISkinView
import com.harreke.easyapp.skin.core.stainer.ISkinStainer
import java.lang.ref.WeakReference
import java.lang.reflect.Constructor
import java.util.concurrent.locks.ReentrantReadWriteLock
import kotlin.concurrent.read
import kotlin.concurrent.write

@SuppressLint("RestrictedApi")
internal class SkinAppCompatDelegate(activity: Activity, callback: AppCompatCallback) : AppCompatDelegateImpl(activity, callback) {
    private val mViewRefs = ArrayList<WeakReference<out ISkinView>>()
    private val mViewLocker = ReentrantReadWriteLock()

    override fun createView(parent: View?, name: String, context: Context, attrs: AttributeSet): View? {
        var view = super.createView(parent, name, context, attrs)
        if (view == null) {
            view = createViewFromTag(context, name, attrs)
        }
        if (view == null || view is ViewStub || view is ViewStubCompat || view is ContentFrameLayout || view is FitWindowsViewGroup) return view
        if (view is ISkinView) {
            mViewLocker.write {
                mViewRefs.add(WeakReference(view))
            }
            view.updateSkin()
            return view
        }
        var stainer: ISkinStainer? = null
        val wrappers = skinViewStainerWrappers
        if (wrappers.isNotEmpty()) {
            for (wrapper in wrappers) {
                stainer = wrapper.wrapView(view, attrs) ?: continue
                break
            }
        }
        if (stainer == null) {
            when (view) {
                is CompoundButton -> {
                    stainer = SkinCompoundButtonStainer(view, attrs)
                }
                is CheckedTextView -> {
                    stainer = SkinCheckedTextViewStainer(view, attrs)
                }
                is TextView -> {
                    stainer = SkinTextViewStainer(view, attrs)
                }
                is ImageView -> {
                    stainer = SkinImageViewStainer(view, attrs)
                }
                is SeekBar -> {
                    stainer = SkinSeekBarStainer(view, attrs)
                }
                is ProgressBar -> {
                    stainer = SkinProgressBarStainer(view, attrs)
                }
                is Toolbar -> {
                    stainer = SkinToolbarStainer(view, attrs)
                }
            }
        }
        if (stainer == null && !attrs.getAttributeBooleanValue(null, "noSkin", false)) {
            stainer = SkinBackgroundStainer(view, attrs)
        }
        if (stainer == null) return view
        mViewLocker.write {
            mViewRefs.add(WeakReference(stainer))
        }
        stainer.updateSkin()
        return view
    }

    fun updateSkin() {
        if (mViewRefs.isEmpty()) return
        mViewLocker.read {
            val local = ArrayList(mViewRefs)
            for (viewRef in local) {
                val view = viewRef.get() ?: continue
                view.updateSkin()
            }
        }
    }

    fun destroy() {
        mViewRefs.clear()
    }

    companion object {
        internal val skinViewStainerWrappers = ArrayList<ISkinStainerWrapper>()
        private val sClassPrefixList = arrayOf(
            "android.widget.",
            "android.view.",
            "android.webkit."
        )
        private val sConstructorMap = SimpleArrayMap<String, Constructor<out View?>>()
        private val sConstructorSignature = arrayOf(Context::class.java, AttributeSet::class.java)

        fun create(activity: Activity, callback: AppCompatCallback) = SkinAppCompatDelegate(activity, callback)

        private fun createViewFromTag(context: Context, name: String, attrs: AttributeSet): View? {
            var actualName = name
            if (actualName == "view") {
                actualName = attrs.getAttributeValue(null, "class")
            }
            return try {
                if (-1 == actualName.indexOf('.')) {
                    for (classPrefix in sClassPrefixList) {
                        val view = createViewByPrefix(context, actualName, classPrefix, attrs)
                        if (view != null) return view
                    }
                    null
                } else {
                    createViewByPrefix(context, actualName, null, attrs)
                }
            } catch (e: Exception) {
                // We do not want to catch these, lets return null and let the actual LayoutInflater
                // try
                null
            }
        }

        private fun createViewByPrefix(context: Context, name: String, prefix: String?, attrs: AttributeSet): View? {
            var constructor = sConstructorMap[name]
            return try {
                if (constructor == null) {
                    // Class not found in the cache, see if it's real, and try to add it
                    val clazz = Class.forName(
                        if (prefix != null) prefix + name else name,
                        false,
                        context.classLoader).asSubclass(View::class.java)
                    constructor = clazz.getConstructor(*sConstructorSignature)
                    sConstructorMap.put(name, constructor)
                }
                if (constructor != null) {
                    constructor.isAccessible = true
                    constructor.newInstance(context, attrs)
                } else {
                    null
                }
            } catch (e: java.lang.Exception) {
                // We do not want to catch these, lets return null and let the actual LayoutInflater
                // try
                null
            }
        }
    }
}