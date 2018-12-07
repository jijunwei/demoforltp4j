# demoforltp4j


哈工大LTP自然语言处理平台的编译使用

Java方式
LTP的Java移植版本ltp4j是采用JNI的方式。Java调用C++编译后的DLL文件，对于DLL文件的方式位置有一定的要求，需要在java.library.path中，有如下几种方式：

DLL文件放在项目目录下，即与src目录同一级别 
可以直接调用ltp4j中诸如Segmentor类及相关方法。

DLL文件放在自定义文件中 
在使用中需要自己显示地调用库，比如：

System.loadLibrary("segmentor"); // 分词

