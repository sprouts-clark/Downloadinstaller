package com.sprouts.appupdater

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dylanc.activityresult.launcher.StartActivityLauncher
import com.sprouts.appupdater.databinding.ActivityMainBinding
import com.sprouts.modulewepay.WeChatPay
import pub.devrel.easypermissions.EasyPermissions


/**
 * Main activity
 *
 * @constructor Create empty Main activity
 */
class MainActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks {
    lateinit var binding: ActivityMainBinding

    //URL 下载有时间效益.自己替换可以正常下载的地址
    private val apkDownLoadUrl =
        "https://lebang-img.4009515151.com/2022/01/12/a6a58983-2059-4e6d-82a0-6a8c763a8806.apk"


    //这个链接会有多次重定向，会有失败问题。请替换自己的测试链接
    private val apkDownLoadUrl2 =
        "https://api.developer.xiaomi.com/autoupdate/updateself/download/fc6b8eba5351dedf2a79dab71c9bb299edcd1fb85AppStore_06af5546730ca4df0191ab263a2ae82b/com.engineer.map_1038.apk"


    private var startActivityLauncher: StartActivityLauncher = StartActivityLauncher(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * 测试升级安装
         */
        binding.update.setOnClickListener { v: View? ->

            Log.d("TAG", "onCreate: " + WeChatPay(this).isPaySupported)

//            WeChatPay(this).weChatPay(
//                "wx36b2c130b322253f",
//                "1900000109",
//                "1101000000140415649af9fc314aa427",
//                "1101000000140429eb40476f8896f4c9",
//                "1398746574",
//                "Sign=WXPay",
//                "oR9d8PuhnIc+YZ8cBHFCwfgpaK9gd7vaRvkYD7rthRAZX+QBhcCYL21N7cHCTUxbQ+EAt6Uy+lwSN22f5YZvI45MLko8Pfso0jm46v5hqcVwrk6uddkGuT+Cdvu4WBqDzaDjnNa5UK3GfE1Wfl2gHxIIY5lLdUgWFts17D4WuolLLkiFZV+JSHMvH7eaLdT9N5GBovBwu5yYKUR7skR8Fu+LozcSqQixnlEZUfyE55feLOQTUYzLmR9pNtPbPsu6WVhbNHMS3Ss2+AehHvz+n64GDmXxbX++IOBvm2olHu3PsOUGRwhudhVf7UcGcunXt8cqNjKNqZLhLw4jqxDg==",
//                ""
//            )


            val data = CheckVersionResult(
                apkDownLoadUrl,
                1,
                "1.0.0",
                "2021-11-11",
                "description",
                0
            )
            UpdateAppDialog(
                this,
                data,
                startActivityLauncher
            ).show()
        }

        /**
         * 测试升级安装2,无效下载链接.
         *
         */
        binding.update2.setOnClickListener { v: View? ->
            val data = CheckVersionResult(apkDownLoadUrl2, 1, "1.0.0", "2021-11-11", "description", 1)
            UpdateAppDialog(this, data, startActivityLauncher)
                .show()
        }


        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
            methodRequiresPermission()
        } else {
            //  低于24即为7.0以下执行内容
        }

        methodRequiresPermission()
    }


    /**
     * 请求权限,创建目录的权限
     */
    private fun methodRequiresPermission() {
        val perms = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (EasyPermissions.hasPermissions(this, *perms)) {
            // Already have permission, do the thing
            // ...
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(this@MainActivity, "App 升级需要储存权限", 10086, *perms)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsGranted(requestCode: Int, list: List<String>) {
        // Some permissions have been granted
        // ...
        val a = 3
    }

    override fun onPermissionsDenied(requestCode: Int, list: List<String>) {
        // Some permissions have been denied
        // ...
        val a = 1
    }

}