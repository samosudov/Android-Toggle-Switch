package belka.us.androidtoggleswitch.widgets

import android.content.Context
import android.support.v4.content.ContextCompat
import android.text.TextUtils
import android.util.AttributeSet
import android.widget.LinearLayout
import belka.us.androidtoggleswitch.R
import java.util.*

abstract class BaseToggleSwitch(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs),
        ToggleSwitchButton.Listener {

    /*
       Default Values
     */

    private val ACTIVE_BACKGROUND_COLOR     = R.color.blue
    private val ACTIVE_BORDER_COLOR         = R.color.blue
    private val ACTIVE_TEXT_COLOR           = android.R.color.white

    private val BORDER_RADIUS_DP            = 4
    private val BORDER_WIDTH                = 0

    private val INACTIVE_BACKGROUND_COLOR   = R.color.gray_light
    private val INACTIVE_BORDER_COLOR       = R.color.gray_light
    private val INACTIVE_TEXT_COLOR         = R.color.gray

    private val LAYOUT_HEIGHT               = LinearLayout.LayoutParams.WRAP_CONTENT
    private val LAYOUT_WIDTH                = LinearLayout.LayoutParams.WRAP_CONTENT

    private val SEPARATOR_COLOR             = R.color.gray_very_light
    private val SEPARATOR_VISIBLE           = true

    private val TEXT_SIZE                   = 16f
    private val TOGGLE_HEIGHT               = 38f
    private val TOGGLE_WIDTH                = 72f


    /*
       Instance Variables
     */


    var activeBackgroundColor: Int
    var activeBorderColor: Int
    var activeTextColor: Int

    var borderRadius: Float
    var borderWidth: Float

    var inactiveBackgroundColor: Int
    var inactiveBorderColor: Int
    var inactiveTextColor: Int

    var separatorColor: Int
    var separatorVisible: Boolean

    var textSize: Float

    var toggleHeight: Float
    var toggleWidth: Float

    var layoutHeight: Int
    var layoutWidth: Int

    var buttons = ArrayList<ToggleSwitchButton>()


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


    fun manageSeparatorVisiblity() {
        for ((index, button) in buttons.withIndex()) {
            if (separatorVisible && index < buttons.size - 1 && !hasBorder()) {
                button.setSeparatorVisibility(button.isChecked == buttons[index + 1].isChecked)
            }
            else {
                button.setSeparatorVisibility(false)
            }
        }
    }


    /*
       Public instance methods
     */

    fun getLabels() : List<String> {
        return buttons.map { it.getText() }
    }

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
        removeAllViews()
        buttons.clear()

        for ((index, entry) in entries.withIndex()) {
            var button = ToggleSwitchButton(context, entry, getPosition(index, entries),
                    this, activeBackgroundColor, activeBorderColor,
                    activeTextColor, borderRadius, borderWidth, inactiveBackgroundColor,
                    inactiveBorderColor, inactiveTextColor, textSize, separatorColor)
            buttons.add(button)
            addView(button)
        }
        manageSeparatorVisiblity()
    }

    public fun reDraw() {
        setEntries(buttons.map { it.getText() })
        onRedrawn()
    }


    /*
       Protected instance methods
     */

    protected abstract fun onRedrawn()

    /*
       Private instance methods
     */

    private fun isFullHeight() : Boolean {
        return layoutHeight == LinearLayout.LayoutParams.MATCH_PARENT
    }

    private fun isFullWidth() : Boolean {
        return layoutWidth == LinearLayout.LayoutParams.MATCH_PARENT
    }

    private fun getPosition(index : Int, entries : List<String>) : ToggleSwitchButton.Position {
        if (index == 0) {
            return ToggleSwitchButton.Position.LEFT
        }
        else if (index == entries.size - 1) {
            return ToggleSwitchButton.Position.RIGHT
        }
        else {
            return ToggleSwitchButton.Position.CENTER
        }
    }

    private fun hasBorder() : Boolean {
        return borderWidth > 0f
    }

    init {
        layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT)
        orientation = HORIZONTAL
        if (attrs != null) {

            val attributes = context.obtainStyledAttributes(attrs, R.styleable.ToggleSwitchOptions, 0, 0)

            try {

                activeBackgroundColor = attributes.getColor(
                        R.styleable.ToggleSwitchOptions_activeBackgroundColor,
                        ContextCompat.getColor(context, ACTIVE_BACKGROUND_COLOR))

                activeBorderColor = attributes.getColor(
                        R.styleable.ToggleSwitchOptions_activeBorderColor,
                        ContextCompat.getColor(context, ACTIVE_BORDER_COLOR))

                activeTextColor = attributes.getColor(
                        R.styleable.ToggleSwitchOptions_activeTextColor,
                        ContextCompat.getColor(context, ACTIVE_TEXT_COLOR))

                borderWidth = attributes.getDimensionPixelSize(
                        R.styleable.ToggleSwitchOptions_borderWidth,
                        dp2px(context, BORDER_WIDTH.toFloat()).toInt()).toFloat()

                inactiveBackgroundColor = attributes.getColor(
                        R.styleable.ToggleSwitchOptions_inactiveBackgroundColor,
                        ContextCompat.getColor(context, INACTIVE_BACKGROUND_COLOR))

                inactiveBorderColor = attributes.getColor(
                        R.styleable.ToggleSwitchOptions_inactiveBorderColor,
                        ContextCompat.getColor(context, INACTIVE_BORDER_COLOR))

                inactiveTextColor = attributes.getColor(
                        R.styleable.ToggleSwitchOptions_inactiveTextColor,
                        ContextCompat.getColor(context, INACTIVE_TEXT_COLOR))

                separatorColor = attributes.getColor(
                        R.styleable.ToggleSwitchOptions_separatorColor,
                        ContextCompat.getColor(context, SEPARATOR_COLOR))

                separatorVisible = attributes.getBoolean(
                        R.styleable.ToggleSwitchOptions_separatorVisible,
                        SEPARATOR_VISIBLE)

                textSize = attributes.getDimensionPixelSize(
                        R.styleable.ToggleSwitchOptions_android_textSize,
                        dp2px(context, TEXT_SIZE).toInt()).toFloat()

                toggleHeight = attributes.getDimension(
                        R.styleable.ToggleSwitchOptions_toggleHeight,
                        dp2px(getContext(), TOGGLE_HEIGHT))

                toggleWidth = attributes.getDimension(
                        R.styleable.ToggleSwitchOptions_toggleWidth,
                        dp2px(getContext(), TOGGLE_WIDTH))

                borderRadius = attributes.getDimensionPixelSize(
                        R.styleable.ToggleSwitchOptions_cornerRadius,
                        dp2px(context, BORDER_RADIUS_DP.toFloat()).toInt()).toFloat()

                layoutHeight = attributes.getLayoutDimension(
                        R.styleable.ToggleSwitchOptions_android_layout_height,
                        LAYOUT_HEIGHT)

                layoutWidth = attributes.getLayoutDimension(
                        R.styleable.ToggleSwitchOptions_android_layout_width,
                        LAYOUT_WIDTH)

                val entries         = attributes.getTextArray(R.styleable.ToggleSwitchOptions_android_entries)
                if (entries == null || entries.isEmpty()) {

                    val entriesList = ArrayList<String>()

                    val textToggleLeft  = attributes.getString(R.styleable.ToggleSwitchOptions_textToggleLeft)
                    val textToggleRight = attributes.getString(R.styleable.ToggleSwitchOptions_textToggleRight)

                    if (!TextUtils.isEmpty(textToggleLeft) && !TextUtils.isEmpty(textToggleRight)) {
                        entriesList.add(textToggleLeft)
                        val textToggleCenter  = attributes.getString(R.styleable.ToggleSwitchOptions_textToggleCenter)
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

}