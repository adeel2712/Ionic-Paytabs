@objc(paytab) class paytab : CDVPlugin {
    func add(command: CDVInvokedUrlCommand) {
        let pluginResult = CDVPluginResult(
            status: CDVCommandStatus_ERROR
        )
        
//        NSNumber *param1 = [[command.arguments objectAtIndex:0] valueForKey:@"param1"]
//        NSNumber *param2 = [[command.arguments objectAtIndex:0] valueForKey:@"param2"]
        
        let msg = command.arguments[0] as? String ?? ""
        
        
        
        self.commandDelegate!.send(
            pluginResult,
            callbackId: command.callbackId
        )
    }
}
