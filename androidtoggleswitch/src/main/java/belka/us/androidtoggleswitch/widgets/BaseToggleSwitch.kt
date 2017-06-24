package belka.us.androidtoggleswitch.widgets

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewCompat
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import belka.us.androidtoggleswitch.R
import java.util.*

abstract class BaseToggleSwitch : LinearLayout, ToggleSwitchButton.Listener {


    /*
       Default Values
     */

    companion object Default {
        @JvmStatic private val ACTIVE_BACKGROUND_COLOR     = R.color.blue
        @JvmStatic private val ACTIVE_BORDER_COLOR         = R.color.blue
        @JvmStatic private val ACTIVE_TEXT_COLOR           = android.R.color.white

        @JvmStatic private val BORDER_RADIUS_DP            = 4
        @JvmStatic private val BORDER_WIDTH                = 0

        @JvmStatic private val EMPTY_DECORATOR             = object: ToggleSwitchButton.ViewDecorator {
            override fun decorate(view: View, position: Int) {}
        }

        @JvmStatic private val ENABLED                     = true

        @JvmStatic private val INACTIVE_BACKGROUND_COLOR   = R.color.gray_light
        @JvmStatic private val INACTIVE_BORDER_COLOR       = R.color.gray_light
        @JvmStatic private val INACTIVE_TEXT_COLOR         = R.color.gray

        @JvmStatic private val LAYOUT_ID                   = R.layout.toggle_switch_button_view
        @JvmStatic private val LAYOUT_HEIGHT               = LinearLayout.LayoutParams.WRAP_CONTENT
        @JvmStatic private val LAYOUT_WIDTH                = LinearLayout.LayoutParams.WRAP_CONTENT

        @JvmStatic private val NUM_ENTRIES                 = 0

        @JvmStatic private val SEPARATOR_COLOR             = R.color.gray_very_light
        @JvmStatic private val SEPARATOR_VISIBLE           = true

        @JvmStatic private val TEXT_SIZE                   = 16f

        @JvmStatic private val TOGGLE_DISTANCE             = 0f
        @JvmStatic private val TOGGLE_ELEVATION            = 0f
        @JvmStatic private val TOGGLE_HEIGHT               = 38f
        @JvmStatic private val TOGGLE_WIDTH                = 72f
    }




    /*
       Instance Variables
     */


    var activeBackgroundColor:      Int
    var activeBorderColor:          Int
    var activeTextColor:            Int

    var borderRadius:               Float
    var borderWidth:                Float

    var inactiveBackgroundColor:    Int
    var inactiveBorderColor:        Int
    var inactiveTextColor:          Int

    var separatorColor:             Int
    var separatorVisible =          SEPARATOR_VISIBLE

    var textSize:                   Float

    var toggleElevation =           TOGGLE_ELEVATION
    var toggleMargin:               Float
    var toggleHeight:               Float
    var toggleWidth:                Float

    var layoutHeight =              LAYOUT_HEIGHT
    var layoutWidth =               LAYOUT_WIDTH

    var layoutId =                  LAYOUT_ID
    var numEntries =                NUM_ENTRIES

    var prepareDecorator:   ToggleSwitchButton.ViewDecorator     = EMPTY_DECORATOR
    var activeDecorator:    ToggleSwitchButton.ViewDecorator?    = null
    var inactiveDecorator:  ToggleSwitchButton.ViewDecorator?    = null

    var buttons = ArrayList<ToggleSwitchButton>()

    /*
       Constructors
     */

    constructor(context: Context) : super(context) {

        // Setup View
        setUpView()

        // Set default params
        activeBackgroundColor       = ContextCompat.getColor(context, ACTIVE_BACKGROUND_COLOR)
        activeBorderColor           = ContextCompat.getColor(context, ACTIVE_BORDER_COLOR)
        activeTextColor             = ContextCompat.getColor(context, ACTIVE_TEXT_COLOR)

        borderRadius                = dp2px(context, BORDER_RADIUS_DP.toFloat())
        borderWidth                 = dp2px(context, BORDER_WIDTH.toFloat())

        inactiveBackgroundColor     = ContextCompat.getColor(context, INACTIVE_BACKGROUND_COLOR)
        inactiveBorderColor         = ContextCompat.getColor(context, INACTIVE_BORDER_COLOR)
        inactiveTextColor           = ContextCompat.getColor(context, INACTIVE_TEXT_COLOR)

        separatorColor              = ContextCompat.getColor(context, SEPARATOR_COLOR)

        textSize                    = dp2px(context, TEXT_SIZE)

        toggleMargin                = dp2px(getContext(), TOGGLE_DISTANCE)
        toggleHeight                = dp2px(getContext(), TOGGLE_HEIGHT)
        toggleWidth                 = dp2px(getContext(), TOGGLE_WIDTH)
    }

    constructor(context: Context, attrs: AttributeSet?) : super (context, attrs) {

        if (attrs != null) {
            setUpView()

            val attributes = context.obtainStyledAttributes(attrs, R.styleable.BaseToggleSwitch, 0, 0)

            try {

                activeBackgroundColor = attributes.getColor(
                        R.styleable.BaseToggleSwitch_activeBackgroundColor,
                        ContextCompat.getColor(context, ACTIVE_BACKGROUND_COLOR))

                activeBorderColor = attributes.getColor(
                        R.styleable.BaseToggleSwitch_activeBorderColor,
                        ContextCompat.getColor(context, ACTIVE_BORDER_COLOR))

                activeTextColor = attributes.getColor(
                        R.styleable.BaseToggleSwitch_activeTextColor,
                        ContextCompat.getColor(context, ACTIVE_TEXT_COLOR))

                borderRadius = attributes.getDimensionPixelSize(
                        R.styleable.BaseToggleSwitch_borderRadius,
                        dp2px(context, BORDER_RADIUS_DP.toFloat()).toInt()).toFloat()

                borderWidth = attributes.getDimensionPixelSize(
                        R.styleable.BaseToggleSwitch_borderWidth,
                        dp2px(context, BORDER_WIDTH.toFloat()).toInt()).toFloat()

                isEnabled = attributes.getBoolean(
                        R.styleable.BaseToggleSwitch_android_enabled,
                        ENABLED)

                inactiveBackgroundColor = attributes.getColor(
                        R.styleable.BaseToggleSwitch_inactiveBackgroundColor,
                        ContextCompat.getColor(context, INACTIVE_BACKGROUND_COLOR))

                inactiveBorderColor = attributes.getColor(
                        R.styleable.BaseToggleSwitch_inactiveBorderColor,
                        ContextCompat.getColor(context, INACTIVE_BORDER_COLOR))

                inactiveTextColor = attributes.getColor(
                        R.styleable.BaseToggleSwitch_inactiveTextColor,
                        ContextCompat.getColor(context, INACTIVE_TEXT_COLOR))

                separatorColor = attributes.getColor(
                        R.styleable.BaseToggleSwitch_separatorColor,
                        ContextCompat.getColor(context, SEPARATOR_COLOR))

                separatorVisible = attributes.getBoolean(
                        R.styleable.BaseToggleSwitch_separatorVisible,
                        SEPARATOR_VISIBLE)

                textSize = attributes.getDimensionPixelSize(
                        R.styleable.BaseToggleSwitch_android_textSize,
                        dp2px(context, TEXT_SIZE).toInt()).toFloat()

                toggleElevation = attributes.getDimensionPixelSize(
                        R.styleable.BaseToggleSwitch_elevation,
                        dp2px(context, TOGGLE_ELEVATION).toInt()).toFloat()

                toggleMargin = attributes.getDimension(
                        R.styleable.BaseToggleSwitch_toggleMargin,
                        dp2px(getContext(), TOGGLE_DISTANCE))

                toggleHeight = attributes.getDimension(
                        R.styleable.BaseToggleSwitch_toggleHeight,
                        dp2px(getContext(), TOGGLE_HEIGHT))

                toggleWidth = attributes.getDimension(
                        R.styleable.BaseToggleSwitch_toggleWidth,
                        dp2px(getContext(), TOGGLE_WIDTH))

                layoutHeight = attributes.getLayoutDimension(
                        R.styleable.BaseToggleSwitch_android_layout_height,
                        LAYOUT_HEIGHT)

                layoutWidth = attributes.getLayoutDimension(
                        R.styleable.BaseToggleSwitch_android_layout_width,
                        LAYOUT_WIDTH)

                val entries         = attributes.getTextArray(R.styleable.BaseToggleSwitch_android_entries)
                if (entries == null || entries.isEmpty()) {

                    val entriesList = ArrayList<String>()

                    val textToggleLeft  = attributes.getString(R.styleable.BaseToggleSwitch_textToggleLeft)
                    val textToggleRight = attributes.getString(R.styleable.BaseToggleSwitch_textToggleRight)

                    if (!TextUtils.isEmpty(textToggleLeft) && !TextUtils.isEmpty(textToggleRight)) {
                        entriesList.add(textToggleLeft)
                        val textToggleCenter  = attributes.getString(R.styleable.BaseToggleSwitch_textToggleCenter)
                        if (!TextUtils.isEmpty(textToggleCenter)) {
                            entriesList.add(textToggleCenter)
                        }
                        entriesList.add(textToggleRight)
                        setEntries(entriesList)
                    }
                }
                else {
                    setEntries(entries)
                }
            }
            finally {
                attributes.recycle()
            }
        }
        else {
            throw RuntimeException("AttributeSet is null!")
        }
    }


    /*
        Overrode instance methods
    */

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        for (button in buttons) {
            if (!isFullWidth()) {
                button.layoutParams.width = toggleWidth.toInt()
            }

            if (!isFullHeight()) {
                button.layoutParams.height = toggleHeight.toInt()
            }
        }
    }

    final override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)

        if (enabled) {
            alpha = 1f
        }
        else {
            alpha = .6f
        }
    }

    final override fun setElevation(elevation: Float) {
        super.setElevation(elevation)
        if (elevation > 0) {
            clipToPadding = false
            setPadding(elevation.toInt(), elevation.toInt(), elevation.toInt(), elevation.toInt())
        }
        else {
            clipToPadding = true
            setPadding(0, 0, 0, 0)
        }
        for (button in buttons) {
            ViewCompat.setElevation(button, elevation)
        }
    }


    /*
        Public instance methods
    */

    fun setEntries(stringArrayId: Int) {
        setEntries(resources.getStringArray(stringArrayId))
    }

    fun setEntries(entries: Array<String>) {
        val entriesList = ArrayList<String>()
        for (entry in entries) {
            entriesList.add(entry)
        }
        setEntries(entriesList)
    }

    fun setEntries(entries : Array<CharSequence>) {
        val entriesList = ArrayList<String>()
        for (entry in entries) {
            entriesList.add(entry.toString())
        }
        setEntries(entriesList)
    }

    fun setEntries(entries : List<String>) {

        val prepareDecorator = object: ToggleSwitchButton.ViewDecorator {
            override fun decorate(view: View, position: Int) {
                val textView = view.findViewById(R.id.text_view) as TextView
                textView.text = entries[position]
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize)
            }
        }

        val activeDecorator = object: ToggleSwitchButton.ViewDecorator {
            override fun decorate(view: View, position: Int) {
                val textView = view.findViewById(R.id.text_view) as TextView
                textView.setTextColor(activeTextColor)
            }
        }

        val inactiveDecorator = object: ToggleSwitchButton.ViewDecorator {
            override fun decorate(view: View, position: Int) {
                val textView = view.findViewById(R.id.text_view) as TextView
                textView.setTextColor(inactiveTextColor)
            }
        }

        setView(R.layout.toggle_switch_button_view, entries.size,
                prepareDecorator, activeDecorator, inactiveDecorator)
    }

    fun setView(layoutId: Int, numEntries: Int,
                prepareDecorator: ToggleSwitchButton.ViewDecorator) {

        setView(layoutId, numEntries, prepareDecorator, null, null)
    }

    fun setView(layoutId: Int, numEntries: Int,
                prepareDecorator: ToggleSwitchButton.ViewDecorator,
                activeDecorator: ToggleSwitchButton.ViewDecorator?,
                inactiveDecorator: ToggleSwitchButton.ViewDecorator?) {
        removeAllViews()
        buttons.clear()

        this.layoutId = layoutId
        this.numEntries = numEntries
        this.activeDecorator = activeDecorator
        this.inactiveDecorator = inactiveDecorator

        for (index in 0..numEntries - 1) {
            val positionType = getPosition(index, numEntries)
            val button = ToggleSwitchButton(context, index, positionType, this,
                    layoutId, prepareDecorator, activeDecorator, inactiveDecorator,
                    activeBackgroundColor, activeBorderColor,
                    borderRadius, borderWidth, inactiveBackgroundColor,
                    inactiveBorderColor, separatorColor, toggleMargin.toInt())
            buttons.add(button)
            addView(button)
        }

        elevation = toggleElevation

        manageSeparatorVisiblity()
    }

    fun reDraw() {
        setView(layoutId, numEntries, prepareDecorator, activeDecorator, inactiveDecorator)
        onRedrawn()
    }


    /*
       Protected instance methods
     */

    protected abstract fun onRedrawn()

    protected fun manageSeparatorVisiblity() {
        for ((index, button) in buttons.withIndex()) {
            if (separatorVisible && index < buttons.size - 1 && !hasBorder() && !areToggleSeparated()) {
                button.setSeparatorVisibility(button.isChecked == buttons[index + 1].isChecked)
            }
            else {
                button.setSeparatorVisibility(false)
            }
        }
    }

    /*
       Private instance methods
     */

    private fun areToggleSeparated() : Boolean {
        return toggleMargin > 0
    }

    private fun getPosition(index : Int, size: Int) : ToggleSwitchButton.PositionType {
        if (index == 0) {
            return ToggleSwitchButton.PositionType.LEFT
        }
        else if (index == size - 1) {
            return ToggleSwitchButton.PositionType.RIGHT
        }
        else {
            return ToggleSwitchButton.PositionType.CENTER
        }
    }

    private fun hasBorder() : Boolean {
        return borderWidth > 0f
    }

    private fun isFullHeight() : Boolean {
        return layoutHeight == LinearLayout.LayoutParams.MATCH_PARENT
    }

    private fun isFullWidth() : Boolean {
        return layoutWidth == LinearLayout.LayoutParams.MATCH_PARENT
    }

    private fun setUpView() {
        layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT)
        orientation = HORIZONTAL
    }

}