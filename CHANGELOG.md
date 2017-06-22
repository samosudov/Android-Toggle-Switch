# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/)
and this project adheres to [Semantic Versioning](http://semver.org/).

## [2.0.0]
### Added
- Support for right-to-left languages.
- Ripple effect on click from Material Design.
- Customizable toggle button dimensions
- Customizable border's color and width
- Customizable separator's color and visibility
- Added `android:entries` attribute to set the entries from xml.
- Added `android:layout_width` and `android:layout_height` attributes to support `match_parent` width and height.
- Added `app:border_width` attribute.
- Added `app:activeBorderColor` and `app:inactiveBorderColor` attributes.
- Added `app:separatorVisible` attribute to set if the separator should be visible or not.
- Added `app:toggleWidth` and `app:toggleHeight` attributes to set the dimensions of the toggle buttons.

### Changed

- `app:activeBgColor` renamed to `app:activeBackgroundColor`.
- `app:inactiveBgColor` renamed to `app:inactiveBackgroundColor`.
- `app:cornerRadius` renamed to `app:borderRadius`

#### Toggle Switch
- Method `getCheckedTogglePosition` renamed to `getCheckedPosition`.
- Method `setCheckedTogglePosition` renamed to `setCheckedPosition`.
- Listener `OnToggleSwitchChangeListener` renamed to `OnChangeListener`.
- Changed listener method `onToggleSwitchChangeListener(int position, boolean checked)` changed to `onToggleSwitchChanged(int position)`.

#### Multiple Toggle Switch
- Method `getCheckedTogglePositions` renamed in `getCheckedPositions` and its return type is `List<Integer>`.
- Method `setCheckedTogglePosition` renamed in `setCheckedPositions`and its argument is now a `List<Integer>`.
- Listener `OnToggleSwitchChangeListener` renamed to `OnChangeListener`.
- Renamed listener method `onToggleSwitchChangeListener` to `onMultipleToggleSwitchChanged`.

### Removed
- Removed method `setUncheckedTogglePosition` from `MultipleToggleSwitch`
