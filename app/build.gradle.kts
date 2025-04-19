plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}
// 这个是安卓特定的属性设置
android {
    namespace = "cn.howxu.android_demo_accounting_book"
    compileSdk = 34

    defaultConfig {
        applicationId = "cn.howxu.android_demo_accounting_book"
        minSdk = 31
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    /**
     * 启用 View Binding（视图绑定）功能。
     * 这是 Android Studio 提供的一个编译时特性，用于更安全、更方便地替代传统的 findViewById() 方式访问界面控件。
     */
    buildFeatures{
        viewBinding = true
    }
}

// 这里是dependence
dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    // implementation(libs.androidx.activity)
    // implementation(libs.androidx.constaintlayout)
    // 测试不需要
    // testImplementation(libs.junit)
    // androidTestImplementation(libs.androidx.junit)
    // androidTestImplementation(libs.androidx.espresso.core)

    // 这俩货不在maven central
    // 视图组件
    implementation("com.github.Dimezis:BlurView:release-1.0.2")
    // Progress Bar 组件
    implementation("com.mikhaellopez:circularprogressbar:3.1.0")

    // lifecycle 管理组件生命周期
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.7")

    // ViewModel 知道VUE吧 这个组件也是响应的
    implementation("androidx.activity:activity-ktx:1.10.1")
    implementation("androidx.activity:activity-compose:1.10.1")
}