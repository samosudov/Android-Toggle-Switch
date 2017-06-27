# Android-Toggle-Switch

![Alt text](https://img.shields.io/badge/license-MIT-green.svg?style=flat)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Android%20Toggle%20Switch-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/3235)
![Alt text](http://www.android-gems.com/badge/BelkaLab/Android-Toggle-Switch.svg)


A customizable extension of Android Switches that supports also more than 2 items.

![Sample of libray](docs/screen.jpg)


## Installation

#### Gradle
Add Gradle dependency:

```groovy
dependencies {
    compile 'us.belka:androidtoggleswitch:1.2.2'
}
```

#### Maven
```xml
<dependency>
  <groupId>us.belka</groupId>
  <artifactId>androidtoggleswitch</artifactId>
  <version>1.2.2</version>
  <type>pom</type>
</dependency>
```

## Basic Usage

![Basic Samples](docs/basic_samples.gif)

#### 3 Items

```xml
<belka.us.androidtoggleswitch.widgets.ToggleSwitch
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        custom:textToggleCenter="XOR"
        custom:textToggleLeft="OR"
        custom:textToggleRight="AND"/>
```

#### N - Items support

This can be accomplished in two ways:
* `xml`: In this way you have to define the `android:entries` attributes
as a `string-array`.
* `programmatically`: you have to set the entries as `List<String>`, `CharSequence[]`, etc.

XML
```xml
<belka.us.androidtoggleswitch.widgets.ToggleSwitch
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:entries="@array/planets"/>
```

Programmatically
```java
ToggleSwitch toggleSwitch = (ToggleSwitch) findViewById(R.id.multiple_switches);
ArrayList<String> labels = new ArrayList<>();
labels.add("AND");
labels.add("OR");
labels.add("XOR");
labels.add("NOT");
labels.add("OFF");
toggleSwitch.setLabels(labels);
```

NOTE: Providing the entries using the `android:entries` attribute,
the attributes textToggle[Left/Center/Right] will be ignored.

#### Multiple checked items support

Simply use `MultipleToggleSwitch` instead of `ToggleSwitch`.

```xml
<belka.us.androidtoggleswitch.widgets.MultipleToggleSwitch
        android:id="@+id/multiple_toggle_switch"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        custom:textToggleCenter="Center"
        custom:textToggleLeft="Left"
        custom:textToggleRight="Right"/>                
```

## Getters and Setters


#### Toggle Switch

* `int getCheckedPosition()` Returns the current checked pos, -1 if none is checked.

```java
int pos = toggleSwitch.getCheckedPosition();
```

* `void setCheckedPosition(int pos)` Checks the pos passed as argument.

```java
int pos = 3;
multipleToggleSwitch.setCheckedPosition(pos);
```

#### Multiple Toggle Switch

* `List<Integer> getCheckedPositions()` Returns the list of the current checked positions

```java
List<Integer> checkedPositions = multipleToggleSwitch.getCheckedPositions();
```

* `void setCheckedPositions(List<Integer> checkedPositions)` Checks the pos passed as argument

```java
List<Integer> checkedPositions = new ArrayList();
checkedPositions.add(3);
checkedPositions.add(5);
multipleToggleSwitch.setCheckedTogglePositions(checkedPositions);
```

## Listeners


#### Toggle Switch

```java
toggleSwitch.setOnChangeListener(new ToggleSwitch.OnChangeListener(){
      @Override
      public void onToggleSwitchChanged(int position) {
	       // Your code ...
      }
  });
```

#### Multiple Toggle Switch

```java
multipleToggleSwitch.setOnChangeListener(new ToggleSwitch.OnChangeListener(){
      @Override
      public void onMultipleToggleSwitchChanged(int position, boolean checked) {
	       // Your code ...
      }
  });
```

## Customization

![Basic Samples](docs/custom.gif)


#### Border

Since by default the `borderWidth` is 0, it is important to set it at least
to `1dp` in order to show the border.

```xml
  <belka.us.androidtoggleswitch.widgets.MultipleToggleSwitch
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:entries="@array/planets"
      app:borderRadius="8dp"
      app:borderWidth="4dp"
      app:checkedBorderColor="@color/teal"
      app:uncheckedBorderColor="@color/gray_border"/>
```

#### Color

```xml
  <belka.us.androidtoggleswitch.widgets.ToggleSwitch
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:entries="@array/planets"
      app:checkedBackgroundColor="@color/orange"
      app:checkedTextColor="@android:color/white"
      app:uncheckedBackgroundColor="@color/colorPrimaryDark"
      app:uncheckedTextColor="@color/green"
      app:separatorColor="@color/green"/>
```

#### Size

```xml
  <belka.us.androidtoggleswitch.widgets.ToggleSwitch
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      app:textToggleLeft="@string/apple"
      app:textToggleRight="@string/lemon"
      android:textSize="26sp"
      app:toggleHeight="82dp"
      app:toggleWidth="122dp"/>
```

In order to let the attributes `app:toggleHeight` and `app:toggleWidth`to work
properly, it is **very** important to set the attributes `android:layout_height`
and `android:layout_width` setted as `wrap_content` respectively.

This is important, because if `android:layout_width=match_parent` then,
the button's width take all the width of the parent distributing the space evenly
for each button.

#### Programmatically Customization

All customizations can be done also programmatically.
Once all the properties has been properly set, it's necessary
to call the method `reDraw()`

![Match parent width and height sample](docs/match_width_height.gif)

## Separated Buttons

![Separated samples](docs/separated.gif)

In order to

## Custom View

![Custom view samples](docs/custom_views.gif)

## Bonus

#### Disabled

In order to disable the toggle switch buttons:
* **xml**: set the attribute `android:enabled=false`
* **programmatically**: ```java toggleSwitch.setEnabled(false)```

#### Elevation

Set the attribute: `android:elevation=<dimension_dp>`

## Attributes

It is possible to customize the buttons applying the following options:


| Option Name      				       | Format          | Description                                      |
| ---------------- 				       | --------------  | -----------------------------                    |
| android:enabled 				       | `boolean`  	   | Enable or disable the toggle switch buttons      |
| android:entries 				       | `array`  	     | Set the labels of each button                    |
| android:textSize 				       | `dimension`  	 | Text size of each button                         |
| custom:checkedBackgroundColor  | `color`         | Background color of a checked button             |
| custom:checkedBorderColor      | `color`         | Border color of a checked button                 |
| custom:checkedTextColor        | `color`         | Text color of a checked button                   |
| custom:borderRadius			       | `dimension`	   | The border radius of each button in dp           |
| custom:borderWidth             | `dimension`     | The width of the border of each button in dp     |
| custom:uncheckedBackgroundColor| `color`		     | Background color of the unchecked buttons        |
| custom:uncheckedBorderColor    | `color`         | Border color of a unchecked button               |
| custom:uncheckedTextColor      | `color`         | Text color of the unchecked buttons              |
| custom:separatorColor          | `color`         | Color of the vertical separator between buttons  |
| custom:separatorVisible        | `boolean`       | Set if the separator is visible or not           |
| custom:toggleMargin    		     | `dimension`     | Margin between each button in dp                 |
| custom:toggleHeight    		     | `dimension`     | Height of each button                            |
| custom:toggleWidth    		     | `dimension`     | Width of each button                             |


## Contributors
Lorenzo Rigato,
Fabrizio Rizzonelli, Android Developer @[Belka](https://github.com/BelkaLab)

## License
Android-Toggle-Switch is Copyright (c) 2016 Belka, srl. It is free software, and may be redistributed under the terms specified in the LICENSE file.  

## About Belka
![Alt text](http://s2.postimg.org/rcjk3hf5x/logo_rosso.jpg)

[Belka](http://belka.us/en) is a Digital Agency specialized in design, mobile applications development and custom solutions.
We love open source software! You can [see our projects](http://belka.us/en/portfolio/) or look at our case studies.

Interested? [Hire us](http://belka.us/en/contacts/) to help build your next amazing project.

[www.belka.us](http://belka.us/en)
