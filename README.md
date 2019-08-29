# 使用配置
添加仓库，最新版本

[![](https://jitpack.io/v/hslooooooool/abcl-l.svg)](https://jitpack.io/#hslooooooool/abcl-l)

```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    implementation 'com.github.hslooooooool:abcl-l:[Tag]'
}
```
引用后APP配置参考，其它配置参考项目根目录下base.gradle配置文件：
```
apply plugin: 'com.android.application'

apply plugin: 'kotlin-android'

apply plugin: 'kotlin-android-extensions'

android {
    compileSdkVersion 29
    buildToolsVersion "29.0.1"
    defaultConfig {
        applicationId "com.example.myapplication"
        minSdkVersion 17
        targetSdkVersion 29
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"

        // apk 方法数超过 65536 限制，进行分包处理
        multiDexEnabled true
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
}

dependencies {
    api fileTree(dir: 'libs', include: ['*.jar'])

    api 'com.github.hslooooooool:abcl-l:1.0'
}
```

## 选型清单
- 网络请求OKHttp&Retrofit
- 请求控制Kotlin Coroutine
- 网络回调数据触发展示响应LiveData.postValue
- 网络回调数据触发动作响应Function.()->Unit
- 官方组件LifeCycle&LiveData&ViewMode&ROOM
- 页面路由ARouter
- 日志工具Timmer
- 事件总线LiveDataBus&EventBus

> 关于LiveData：若View在不处于活动状态时IO线程多次变更LiveData，当View重新处于活动状态时将收到最后一条对LiveData的变化推送，不会像消息队列一样重新发送未到达的消息，因此规定在LiveData.Observe中仅用于数据展示变化，不进行逻辑处理，否者可能导致View.stop->View.resume时LiveData.Observe重复执行；因此对逻辑处理必须在主线程进行，否则直接丢弃处理。

## 模块设计

### 1级层面-数据结构与常量管理模块
数据结构作为系统设计的最重要一环，承载了后续接口设计、逻辑处理等工作的开展，所以作为最底层依赖，供所有模块使用。

常量管理包括路由地址管理、全局常量管理、框架配置管理等。

### 2级层面-基础架构模块
基础架构定义项目后续研发的基准开发与编码方式，如MVC/MVP/MVVM的开发方式，尽可能的成都统一规范开发标准。

包括以下几点：
- MVC/MVP/MVVM基础设计搭建——抽象实现
- 各场景下的逻辑处理标准——抽象实现
- Activity页面管理
- Module生命周期管理

### 2级层面-独立功能模块
独立功能模块涵盖所有可单独实现的功能，涵盖以下功能：
- 网络请求
- 图片加载
- 异常捕获
- 日志系统
- 埋点统计
- 缓存管理
- 文件上传与下载
- 文件选择（拍照、录像、录音、文件选择）
- 文件解压缩
- 文件读写工具
- web容器
- JsBridge调用

### 3级层面-基础业务模块
基础业务架构定义项目统一处理逻辑与实现，如网络请求错误统一处理方式、通用异常处理逻辑，以及各类业务的通用处理逻辑实现。

### 4级层面-独立业务模块
独立业务模块为具体业务的唯一实现，以开发的接口方式对外提供服务。

### 5级层面-Module壳
作为独立模块的壳工程，使Module可独立打包运行并进行测试。

创建独立Module时自动生成，减少工作量。

### 5级层面-APP壳
打包、混淆配置

# 签名信息

项目根目录依次执行：
```
keytool -genkey -alias qsos-abcl-lib -keypass qsos-abcl-lib-password -keyalg RSA -keysize 1024 -validity 36500 -keystore qsos-abcl-lib-release.keystore -storepass qsos-abcl-lib-password
```

```
keytool -importkeystore -srckeystore qsos-abcl-lib-release.keystore -destkeystore qsos-abcl-lib-release.keystore -deststoretype pkcs12
```

项目APP的Gradle配置：
```
release {
       keyAlias 'qsos-abcl-lib'
       keyPassword 'qsos-abcl-lib-password'
       storeFile file('../qsos-abcl-lib-release.keystore')
       storePassword 'qsos-abcl-lib-password'
}
```

# 参考
[有赞商城-Android组件化方案](https://tech.youzan.com/you-zan-yi-dong-androidzu-jian-hua-fang-an/)
