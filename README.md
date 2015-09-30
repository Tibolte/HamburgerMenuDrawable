HamburgerMenuDrawable
=================

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-HamburgerMenuDrawable-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/915)

Recently I came across some articles on Dribble which were showing some animated menu buttons, commonly called Burger buttons.
An example of this is illustrated on this page: https://dribbble.com/shots/1654493-Hamburger-Icon-in-Framer?list=users&offset=1

After a bit of search and help of Flavien Laurent and his project TickPlusDrawable (https://github.com/flavienlaurent/tickplusdrawable),
I found a simple solution involving subclassing the Drawable class. Then we just have to play with AnimatorSet and ObjectAnimator
to animate the lines and make a cross and a hamburger appear successively. Here is the result:

![](https://raw.githubusercontent.com/Tibolte/MenuCrossDrawable/master/buttoncrossmenudemosvg.gif)


##Android

Tested on APIs 2.2+, requires the nineOldAndroid jar file included


## License

HamburgerMenuDrawable is available under the MIT license. See the LICENSE file for more info.
