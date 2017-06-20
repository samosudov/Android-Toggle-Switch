# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/)
and this project adheres to [Semantic Versioning](http://semver.org/).

## [2.0.0]
### Added
- Support for right-to-left languages.
- Ripple effect on click from Material Design.
- `match_parent` support
- Customizable toggle button dimensions
- Customizable border's color and width
- Customizable separator's color and visibility
- Added `android:entries` attribute to set the entries from xml
### Changed

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
