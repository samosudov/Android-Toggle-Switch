package belka.us.androidtoggleswitch.widgets

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.support.v4.view.ViewCompat
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import belka.us.androidtoggleswitch.R


class ToggleSwitchButton : LinearLayout {

    interface Listener {
        fun onToggleSwitchClicked(button: ToggleSwitchButton)
    }

    enum class Position {
        LEFT, CENTER, RIGHT
    }

    var activeBackgroundColor: Int
    var activeBorderColor: Int
    var activeTextColor: Int

    var borderRadius: Float
    var borderWidth: Float

    var inactiveBackgroundColor: Int
    var inactiveBorderColor: Int
    var inactiveTextColor: Int

    var separatorColor: Int

    var textSize: Float

    var toggleMargin: Int
    var toggleWidth: Int
    var toggleHeight: Int

    var isChecked: Boolean

    var position: Position
    var textView: TextView
    var separator: View

    var listener: Listener

    constructor(context: Context, entry: String, position: Position, listener: Listener,
                activeBackgroundColor: Int, activeBorderColor: Int, activeTextColor: Int,
                borderRadius: Float, borderWidth: Float, inactiveBackgroundColor: Int,
                inactiveBorderColor: Int, inactiveTextColor: Int, textSize: Float,
                separatorColor: Int, toggleMargin: Int) : super(context) {

        this.isChecked                  = false
        this.position                   = position
        this.listener                   = listener

        this.activeBackgroundColor      = activeBackgroundColor
        this.activeBorderColor          = activeBorderColor
        this.activeTextColor            = activeTextColor

        this.borderRadius               = borderRadius
        this.borderWidth                = borderWidth

        this.inactiveBackgroundColor    = inactiveBackgroundColor
        this.inactiveBorderColor        = inactiveBorderColor
        this.inactiveTextColor          = inactiveTextColor

        this.separatorColor             = separatorColor
        this.textSize                   = textSize

        this.toggleMargin             = toggleMargin
        this.toggleWidth                = 0
        this.toggleHeight               = LinearLayout.LayoutParams.MATCH_PARENT


        // Inflate Layout

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var view = inflater.inflate(R.layout.toggle_switch_button, this, true)

        // Bind Views

        textView = view.findViewById(R.id.text_view) as TextView
        separator = view.findViewById(R.id.separator)
        val clickableWrapper = findViewById(R.id.clickable_wrapper)

        // Setup View

        val layoutParams = LinearLayout.LayoutParams(toggleWidth, toggleHeight, 1.0f)

        if (toggleMargin > 0 && !isFirst()) {
            layoutParams.setMargins(toggleMargin, 0, 0, 0)
        }

        this.layoutParams = layoutParams

        this.orientation = HORIZONTAL
        this.background = getBackgroundDrawable(position, inactiveBackgroundColor,
                inactiveBorderColor, borderRadius, borderWidth, toggleMargin)

        separator.setBackgroundColor(separatorColor)

        textView.text = entry
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize)
        textView.setTextColor(inactiveTextColor)

        clickableWrapper.setOnClickListener {
            listener.onToggleSwitchClicked(this)
        }

        ViewCompat.setElevation(this, 40f)
    }

    fun check() {
        this.background = getBackgroundDrawable(position, activeBackgroundColor,
                activeBorderColor, borderRadius, borderWidth, toggleMargin)
        this.textView.setTextColor(activeTextColor)
        this.isChecked = true
    }

    fun uncheck() {
        this.background = getBackgroundDrawable(position, inactiveBackgroundColor,
                inactiveBorderColor, borderRadius, borderWidth, toggleMargin)
        this.textView.setTextColor(inactiveTextColor)
        this.isChecked = false
    }

    fun getText() : String {
        return textView.text.toString()
    }

    public fun setSeparatorVisibility(isSeparatorVisible : Boolean) {
        separator.visibility = if (isSeparatorVisible) View.VISIBLE else View.GONE
    }

    // Private instance methods

    private fun getBackgroundDrawable(position: Position, backgroundColor : Int, borderColor : Int,
                              borderRadius: Float, borderWidth : Float,
                                      toggleMargin: Int) : GradientDrawable {

        val isRightToLeft = resources.getBoolean(R.bool.is_right_to_left)

        var gradientDrawable = GradientDrawable()
        gradientDrawable.setColor(backgroundColor)

        gradientDrawable.cornerRadii = getCornerRadii(position, isRightToLeft, borderRadius, toggleMargin)

        if (borderWidth > 0) {
            gradientDrawable.setStroke(borderWidth.toInt(), borderColor)
        }

        return gradientDrawable
    }

    private fun getCornerRadii(position: Position, isRightToLeft: Boolean, borderRadius: Float, toggleMargin: Int): FloatArray {
        if (toggleMargin > 0) {
            return floatArrayOf(borderRadius,borderRadius,borderRadius, borderRadius, borderRadius, borderRadius, borderRadius,borderRadius)
        }
        else {
            when(position) {
                Position.LEFT ->
                    return if (isRightToLeft) getRightCornerRadii(borderRadius) else getLeftCornerRadii(borderRadius)

                Position.RIGHT ->
                    return if (isRightToLeft) getLeftCornerRadii(borderRadius) else getRightCornerRadii(borderRadius)

                else -> return floatArrayOf(0f,0f,0f, 0f, 0f, 0f, 0f,0f)
            }
        }
    }

    private fun getRightCornerRadii(borderRadius: Float): FloatArray {
        return floatArrayOf(0f,0f,borderRadius, borderRadius, borderRadius, borderRadius, 0f,0f)
    }

    private fun getLeftCornerRadii(borderRadius: Float): FloatArray {
        return floatArrayOf(borderRadius, borderRadius, 0f,0f,0f,0f, borderRadius, borderRadius)
    }

    private fun isFirst() : Boolean {
        val isRightToLeft = resources.getBoolean(R.bool.is_right_to_left)

        if (isRightToLeft) {
            return position == Position.RIGHT
        }
        else {
            return position == Position.LEFT
        }
    }
}