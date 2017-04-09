# MixUtils
A quick import to the library of commonly used open source libraries

### Getting started

In your ```build.gradle:```

```
dependencies {
    compile 'me.guowenlong.mixutils:mix-utils:0.1.1'
}
```
### Usage
Step 1: initialize
```
public class MyApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		MixUtils.install(this);
	}
}
```
Step 2:you need to specify your Application class in AndroidManifest.xml.

```
<manifest
    ...
    >
    <uses-permission android:name="android.permission.INTERNET" />
    <application
      ...
      android:label="@string/app_name"
      android:name=".MyApplication"
      >
      ...
    </application>
    ...
  </manifest>
```
Step 3:The lib can fast import these open libraries or implement some functions (Samples are coding.......)
- MVP
- Retrofit2
- Rxjava
- Fresco
- SwipeBack
- Xlog

### Contain
The lib contain these open libraries
```
dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])

    compile 'com.android.support:design:25.1.0'
    compile 'com.android.support:percent:25.1.0'
    compile 'com.android.support:cardview-v7:25.1.0'
    compile 'com.android.support:recyclerview-v7:25.1.0'
    compile 'com.android.support:support-annotations:25.1.0'

    compile 'com.squareup.retrofit2:retrofit:2.1.0'
    compile 'com.squareup.retrofit2:converter-gson:2.1.0'
    compile 'com.squareup.retrofit2:adapter-rxjava:2.1.0'
    compile 'com.squareup.okhttp3:logging-interceptor:3.4.1'

    compile 'io.reactivex:rxjava:1.1.9'
    compile 'io.reactivex:rxandroid:1.2.1'

    compile 'com.facebook.fresco:fresco:1.0.1'

    compile 'com.elvishew:xlog:1.3.0'

    compile 'com.jakewharton:butterknife:8.5.1'
    annotationProcessor 'com.jakewharton:butterknife-compiler:8.5.1'

    compile 'com.github.liuguangqiang.swipeback:library:1.0.2@aar'
}
```
### To-Do List
- [x] 接入MVP架子
- [x] 接入Retrofit2
- [x] 接入Rxjava
- [x] 接入Fresco
- [x] 接入SwipeBack
- [x] 接入滑动返回功能
- [x] 接入Xlog
- [x] 性能监控库的接入
- [ ] RxJava2替换RxJava1
- [ ] 增加samples
- [ ] 加密解密

## License
```
Copyright 2017 Wenlong-Guo

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

