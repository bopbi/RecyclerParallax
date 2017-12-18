# RecyclerParallax
Android Recycler View with Parallax Effect on each item

# How to use
This in an example app, not a library so in order to implement it please take a look at these files:
- ParallaxAdapter.java -> on the method bindView it is set the image to-be parallaxed as the background with specific size, and provide the method retranslate to translate the image
- ParallaxRecyclerView.java -> custom recyclerview that bind the recyclerview with the scroll listener
- ParallaxScrollListener.java -> custom Scroll listener that perform the call Adapter retranslate method  

basically the technique is use the scroll listener to call every viewholder to translate the background image based on its position

License
--------

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       https://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

[Demo Video](https://youtu.be/8dxKiSZr24s)

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-RecyclerParallax-green.svg?style=true)](https://android-arsenal.com/details/1/2794)