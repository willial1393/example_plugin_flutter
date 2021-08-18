package com.example.example_plugin_flutter

import android.os.Build
import androidx.annotation.NonNull

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import java.lang.Exception

/** ExamplePluginFlutterPlugin */
class ExamplePluginFlutterPlugin: FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private var channel: MethodChannel? = null
  private var synth: Synth? = null
  private val channelName = "example_plugin_flutter"

  private fun setup(plugin: ExamplePluginFlutterPlugin, binaryMessenger: BinaryMessenger) {
    plugin.channel = MethodChannel(binaryMessenger, channelName)
    plugin.channel!!.setMethodCallHandler(plugin)
    plugin.synth = Synth()
    plugin.synth!!.start()
  }

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    setup(this, flutterPluginBinding.binaryMessenger);
  }

  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
    when {
      call.method.equals("getPlatformVersion") -> {
        result.success("Android " + Build.VERSION.RELEASE)
      }
      call.method.equals("onKeyDown") -> {
        try {
          val arguments: ArrayList<Any> = call.arguments as ArrayList<Any>
          val numKeysDown = synth!!.keyDown((arguments.get(0) as Int))
          result.success(numKeysDown)
        } catch (ex: Exception) {
          result.error("1", ex.message, ex.stackTrace)
        }
      }
      call.method.equals("onKeyUp") -> {
        try {
          val arguments: ArrayList<Any> = call.arguments as ArrayList<Any>
          val numKeysDown = synth!!.keyUp((arguments.get(0) as Int))
          result.success(numKeysDown)
        } catch (ex: Exception) {
          result.error("1", ex.message, ex.stackTrace)
        }
      }
      else -> {
        result.notImplemented()
      }
    }
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    channel?.setMethodCallHandler(null)
  }
}
