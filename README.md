# Android app: keep track of spendings

## Description

Many apps exist out there to keep track of your spendings. But it's amazing how much more useful an app can be when it is tailored exactly to the user's personal needs, even if the app is very simple. So with the <a href="https://en.wikipedia.org/wiki/Android_Studio">Android Studio IDE</a>, this app called <b>MySpend</b> was created, using <a href="https://en.wikipedia.org/wiki/Java">Java</a>, <a href="https://en.wikipedia.org/wiki/XML">XML</a>, a <a href="https://en.wikipedia.org/wiki/Graphical_user_interface_builder">UI builder</a>, and the other backend provided by the IDE.

The idea is that every time you purchase something, an entry can be created in the app, inputting the amount, currency, and category. When "submit" is clicked, the app makes an entry (with a timestamp) in a TXT file stored on the phone. That way, at the end of the month for example, the user can copy this file to their computer, and then parse the data to plot it and have an idea of spendings.

Using this code as a template, other users can also fork this project and create their own implementation.

## Example usage

Here is an example of making an entry:

<img src="https://github.com/nullberg/MySpend/blob/master/images/MySpend_entry_example.png" alt="MySpend_entry_example.png" width="600px"/>

The spend entries are stored locally on the phone in <code>/storage/emulated/0/Documents/MySpend/myspend_data.txt</code>. Example contents of this file are shown below from having opened the file with a text editor on the phone:

<img src="https://github.com/nullberg/MySpend/blob/master/images/MySpend_data_file.png" alt="MySpend_data_file.png" width="600px"/>
