# EasyApp-Skin
An adaptive framework that can automatically attach skin-support ability to android (native and custom) views.
![screenrecord](https://user-images.githubusercontent.com/8429287/195154855-322f28a2-6b80-4ced-92e5-775a33fbbab3.gif)

### Features:
#### 1. Non-intrusive framework.
No views will be replaced with SkinXXXView, you do not need to extend any view to attach skin-support ability (eg. TextView -> SkinTextView).
This framework use some helper classes called stainers to apply colors and drawables to original views, such as Toolbar, framework won't create a SkinTextView class witch extends TextView (parse resources and change color from inside of view), but create a helper class SkinTextViewStainer (parse and change from outside of view).

#### 2. Component based design.
Stainers are combined with different parts, like SkinTextViewStainer, it extends the base part SkinBackgroundStainer, with gives stainer abilities to solve Vew base attributes: background and backgroundTint. Also, it has textViewHelper to solve TextView advanced attributes: textColor, textColorHint, drawableLeft, drawableTop, drwableRight and drwaableBottom, this SkinTextViewStainer supports any android views that extend TextView, such as EditText, Button, CheckedTextView etc..
Another example SkinCompoundButtonStainer, it extends SkinTextViewStainer and also has compoundButtonHelper, whitch means it has full features of TextView skin support, and button, buttonTint attributes solution, this SkinCompoundButtonStainer supports any android views that extend CompoundButton, such as RadioButton, CheckBox, Switch, ToggleButton etc.

#### 3. Highly adaptive.
By collocating base stainer and different attribute solver helper class, you can easliy create new stainer to adapt any exist read-only (or your own custom) android views.


### Usage:
#### 1. Initialization.
1.1. Insert code below into your application's onCreate() method:
``` kotlin
SkinManager
    .addStainerWrapper(SkinMaterialStainerWrapper())
    .addStainerWrapper(SkinCardViewStainerWrapper())
    .loadSkin(this)
```
1.2. Make sure your base activity extends SkinBaseActivity.
Or else, if you don't want to use SkinBaseActivity, then insert interface  **ISkinObserver** to your base activity's implemetations, and insert code below into your base activity body:
``` kotlin
open class YourBaseActivity : AppCompatActivity(), ... ISkinObserver {
    ...
    override fun getDelegate() = SkinManager.createDelegate(this) ?: super.getDelegate()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SkinManager.addObserver(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        SkinManager.destroyDelegate(this)
        SkinManager.removeObserver(this)
    }

    final override fun updateSkin() {
        SkinManager.updateDelegate(this)
        applySkin()
    }

    protected open fun applySkin() {
        //TODO custom skin apply action
    }
    ...
}
```
#### 2. XML layout coding.
Nothing changes, write xml code just like before. Any view written in xml layouts will be automatically solved and attached with their correspoinding stainers.

#### 3. Skin resources preparation.
All skin resource files should have suffix related to skin name.
Assuming you wants to add a skin named "night" into app, all resources that need to change should have their "night" variant.
Such as:
**colors**
```
<color name="layout_color">#CCCCCC</color>
<color name="layout_color_night">#333333</color>

<color name="bg_color">#FFFFFF</color>
<color name="bg_color_night">#000000</color>

<color name="tv_color">#000000</color>
<color name="tv_color_night">#FFFFFF</color>

<color name="tint_color">#FF0000</color>
<color name="tint_color_night">#00FF00</color>
```
**files**
```
ic_logo.jpg --> ic_logo_night.jpg
ic_button_confirm.jpg --> ic_button_confirm_night.jpg
shape_circle.xml --> shape_circle_night.xml
```
The variants naming convetions is: **your resource name** **_** **your skin name**, no blanks.

#### 4. Custom views
Make sure your custom views implement interface **ISkinView**, then you can handle skin change actions in method **updateSkin()**.
For example:
``` kotlin
class YourCustomView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    // record the resource id of color
    private var mColorId = 0
    private var mDrawableId = 0
    
    init {
        val style = context. obtainStyledAttributes(attrs, R.styleable.YourCustomView)
        //parse some attributes
        ...
        //parse resource ids which you want to change
        mColorId = style.getResourceId(R.styleable.YourCustomView_color, 0)
        mDrawableId = style.getResourceId(R.styleable.YourCustomView_drawable, 0)
        style.recycle()
    }
    
    override fun updateSkin() {
        // solve the corresponding skin resource value
        if (mColorId != 0) {
            val skinColor = SkinResourceManager.getSkinColor(context, mColorId)
            //skinColor will be the actual skin color
        }
        if (mDrawableId != 0) {
            val skinDrawable = SkinResourceManager.getSkinDrawable(context, mDrawableId)
            //skinDrawable will be the actual skin drawable
        }
    }
}
```
To take effect, use code below when activity lifecycle onResume() is called:
``` kotlin
SkinManager.loadSkin(context, skinName)
```

### Thanks:
This framework is inspired by > ximsfei/Android-skin-support
