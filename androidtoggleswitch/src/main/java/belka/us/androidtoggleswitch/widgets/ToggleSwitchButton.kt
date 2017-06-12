package belka.us.androidtoggleswitch.widgets

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import belka.us.androidtoggleswitch.R



/**
 * Created by lorenzorigato on 01/06/2017.
 */

class ToggleSwitchButton : LinearLayout {

    interface Listener {
        fun onToggleSwitchClicked(button: ToggleSwitchButton)
    }

    enum class Position {
        LEFT, CENTER, RIGHT
    }

    var activeBackgroundColor : Int
    var activeBorderColor : Int
    var activeTextColor : Int

    var borderRadius : Float
    var borderWidth : Float

    var inactiveBackgroundColor : Int
    var inactiveBorderColor : Int
    var inactiveTextColor : Int

    var separatorColor : Int
    var textSize : Int

    var isChecked : Boolean

    var position : Position
    var textView : TextView
    var separator : View

    var listener : Listener

    constructor(context: Context, entry: String, position: Position, listener: Listener,
                activeBackgroundColor: Int, activeBorderColor: Int, activeTextColor: Int,
                borderRadius: Float, borderWidth: Float, inactiveBackgroundColor: Int,
                inactiveBorderColor: Int, inactiveTextColor: Int, textSize: Int, separatorColor: Int) : super(context) {

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


        // Inflate Layout

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var view = inflater.inflate(R.layout.toggle_switch_button, this, true)


        // Bind Views

        textView = view.findViewById(R.id.text_view) as TextView
        separator = view.findViewById(R.id.separator)
        val clickableWrapper = findViewById(R.id.clickable_wrapper)

        // Setup View

        this.layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f)
        this.orientation = HORIZONTAL
        this.background = getBackgroundDrawable(position, inactiveBackgroundColor,
                inactiveBorderColor, borderRadius, borderWidth)

        separator.setBackgroundColor(separatorColor)
        if (isRight()) {
            separator.visibility = View.GONE
        }

        textView.text = entry
        textView.setTextColor(inactiveTextColor)

        val increment : Int = dp2px(getContext(), 8f).toInt()
        separator.layoutParams.height = textSize + increment
        separator.requestLayout()

        clickableWrapper.setOnClickListener {
            isChecked = !isChecked
            if (isChecked) {
                check()
            }
            else {
                uncheck()
            }
            listener.onToggleSwitchClicked(this)
        }
    }


    fun isRight() : Boolean {
        return position == Position.RIGHT
    }

    fun check() {
        this.background = getBackgroundDrawable(position, activeBackgroundColor,
                activeBorderColor, borderRadius, borderWidth)
        this.textView.setTextColor(activeTextColor)
        this.isChecked = true
    }

    fun uncheck() {
        this.background = getBackgroundDrawable(position, inactiveBackgroundColor,
                inactiveBorderColor, borderRadius, borderWidth)
        this.textView.setTextColor(inactiveTextColor)
        this.isChecked = false
    }

    public fun setSeparatorVisibility(isSeparatorVisible : Boolean) {
        separator.visibility = if (isSeparatorVisible) View.VISIBLE else View.GONE
    }

    // Private instance methods

    private fun getBackgroundDrawable(position: Position, backgroundColor : Int, borderColor : Int,
                              borderRadius: Float, borderWidth : Float) : GradientDrawable {
        var gradientDrawable = GradientDrawable()
        gradientDrawable.setColor(backgroundColor)

        when(position) {
            Position.LEFT -> gradientDrawable.cornerRadii = floatArrayOf(
                    borderRadius, borderRadius, 0f,0f,0f,0f, borderRadius, borderRadius)

            Position.RIGHT -> gradientDrawable.cornerRadii = floatArrayOf(
                    0f,0f,borderRadius, borderRadius, borderRadius, borderRadius, 0f,0f)
        }

        if (borderWidth > 0) {
            gradientDrawable.setStroke(borderWidth.toInt(), borderColor)
        }

        return gradientDrawable
    }
}