
import 'dart:async';

import 'package:flutter/services.dart';

class ExamplePluginFlutter {
  static const MethodChannel _channel =
      const MethodChannel('example_plugin_flutter');

  static Future<String?> get platformVersion async {
    final String? version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }
}
