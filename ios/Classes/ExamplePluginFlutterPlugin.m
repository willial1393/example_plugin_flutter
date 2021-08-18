#import "ExamplePluginFlutterPlugin.h"
#if __has_include(<example_plugin_flutter/example_plugin_flutter-Swift.h>)
#import <example_plugin_flutter/example_plugin_flutter-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "example_plugin_flutter-Swift.h"
#endif

@implementation ExamplePluginFlutterPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftExamplePluginFlutterPlugin registerWithRegistrar:registrar];
}
@end
