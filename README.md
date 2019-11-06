# Android app: keep track of spendings

## Description

Many apps exist out there to keep track of your spendings. But it's amazing how much more useful an app can be when it is tailored EXACTLY to the user's personal needs, even if the app is very simple. So with the <a href="https://en.wikipedia.org/wiki/Android_Studio">Android Studio IDE</a>, I programmed a simple app called <b>MySpend</b>, using <a href="https://en.wikipedia.org/wiki/Java">Java</a>, <a href="https://en.wikipedia.org/wiki/XML">XML</a>, a <a href="https://en.wikipedia.org/wiki/Graphical_user_interface_builder">UI builder</a>, and the other backend provided by the IDE.

The idea is that every time I purchase something, I create an entry in my app, inputting the amount, currency, and category. When I click "submit", the app makes an entry (with a timestamp) in a TXT file stored on my phone. That way, at the end of the month for example, I can copy this file to my computer, and then parse the data to plot it and have an idea of my spendings.

## Example usage