# Ky-log
> 一个可以可视化、打印堆栈、文件打印、自定义解析格式化的打印框架
> 
> A log framework that can visualize, print stack, print file, and customize parsing and formatting
## Gradle:
Add it in your root build.gradle at the end of repositories:
```gradle
allprojects {
 repositories {
 	...
 	maven { url 'https://jitpack.io' }
 }
}
```
## Tag:
[![](https://www.jitpack.io/v/kekegdsz/Ky-log.svg)](https://www.jitpack.io/#kekegdsz/Ky-log)

Add the dependency
```gradle
dependencies {
         implementation 'com.github.kekegdsz:Ky-log:Tag'
}
```

## USE:

init

```gradle
class MApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKyLog()
    }

    private fun initKyLog() {
        val absolutePath = this.cacheDir.absolutePath
        KyLogManager.init(object : KyLogConfig() {

            override fun injectJsonParser(): JsonParser {
                return JsonParser { src -> Gson().toJson(src) }
            }

            override fun includeThread(): Boolean {
                return true
            }

            override fun getGlobalTag(): String {
                return "KyLog"
            }

            override fun enable(): Boolean {
                return true
            }
        }, KyConsolePrinter(), KyFilePrinter.getInstance(absolutePath, 0))
        KyLog.i(absolutePath)
    }
}
```

```gradle
  KyLog.i("ky-log")
```



License
----------
```text
Copyright 2021 Ke Ke

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
