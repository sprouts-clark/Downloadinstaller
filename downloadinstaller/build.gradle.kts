import com.vanniktech.maven.publish.SonatypeHost

plugins {
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    // 应用 Android 库插件
    id("com.android.library")
    // 应用 Kotlin Android 插件
    // 应用 Kotlin KAPT 插件
    kotlin("kapt")
    // 应用 Maven 发布插件
    id("com.vanniktech.maven.publish") version "0.30.0"
}

android {
    namespace = "com.sprouts.downloadinstaller"
    compileSdk = 35

    defaultConfig {
//        applicationId = "com.sprouts.downloadinstaller"
        minSdk = 26
        targetSdk = 35
//        versionCode = 1
//        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        viewBinding = true
        dataBinding = true
    }
}

mavenPublishing {

    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
    coordinates("io.github.sprouts-clark", "DownloadInstaller", "1.0.0")

    pom {
        name.set("DownloadInstaller")
        description.set("a library uesed for apps' upgrade")
        inceptionYear.set("2025")
        url.set("https://github.com/AnyLifeZLB/DownloadInstaller/tree/master")
        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }
        developers {
            developer {
                id.set("sprouts-clark")
                name.set("sprouts-clark")
                url.set("https://github.com/sprouts-clark")
            }
        }
        scm {
            url.set("https://github.com/sprouts-clark/Downloadinstaller/")
            connection.set("scm:git:git://github.com/sprouts-clark/Downloadinstaller.git")
            developerConnection.set("scm:git:ssh://git@github.com/sprouts-clark/Downloadinstaller.git")
        }
    }
}


tasks.register<Javadoc>("androidJavadocs") {
    // 注册生成 Javadoc 的任务
    source = files(android.sourceSets["main"].java.srcDirs).asFileTree
    // 将 Android 的引导类路径添加到 Javadoc 类路径中
    classpath += files(android.bootClasspath.joinToString(File.pathSeparator))
    // 遍历所有库变体
    android.libraryVariants.all { variant ->
        if (variant.name == "release") {
            classpath += variant.javaCompileProvider.get().classpath
        }
        true
    }
    exclude("**/R.html", "**/R.*.html", "**/index.html")
}


//// 注册生成 Javadoc Jar 包的任务，依赖于 androidJavadocs 任务
tasks.register<Jar>("androidJavadocsJar") {
    dependsOn("androidJavadocs")
    // 设置归档文件的分类器
    archiveClassifier.set("javadoc")
    //设置文档名称
    archiveBaseName.set("DownloadInstaller-1.0.0")
    // 使用 tasks.named 获取类型安全的 Javadoc 任务引用
    from(tasks.named<Javadoc>("androidJavadocs").get().destinationDir)
}

// 注册生成源文件 Jar 包的任务
tasks.register<Jar>("sourceJar") {
    // 指定 Jar 包的内容来源为 main 源集的 Java 源文件目录
    from(android.sourceSets["main"].java.srcDirs)
    // 设置归档文件的分类器
    archiveClassifier.set("sources")
    archiveBaseName.set("DownloadInstaller-1.0.0")
}

tasks.register("generateJavadocSha1") {
    val jarFile = File("$buildDir/libs/DownloadInstaller-1.0.0-javadoc.jar")
    val sourceFile = File("$buildDir/libs/DownloadInstaller-1.0.0-sources.jar")
    val aarFile = File("$buildDir/outputs/aar/downloadinstaller-release.aar")
    val pomFile = File("$buildDir/publications/maven/pom-default.xml")

    val listFile = arrayOf(jarFile, sourceFile, aarFile, pomFile)
    for (file in listFile) {
        if (file.exists()) {
            val sha1 = org.apache.commons.codec.digest.DigestUtils.sha1Hex(file.readBytes())
            val sha1File = File("${file.absolutePath}.sha1")
            sha1File.writeText(sha1)
            val md51 = org.apache.commons.codec.digest.DigestUtils.md5Hex(file.readBytes())
            val md51File = File("${file.absolutePath}.md5")
            md51File.writeText(md51)

        }

    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // 从 libs 目录中包含所有的 .jar 文件
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    // AndroidX ConstraintLayout 库
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")
    // Activity 结果启动器库
    implementation("com.github.DylanCaiCoding:ActivityResultLauncher:1.1.2")
}