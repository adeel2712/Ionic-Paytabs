import Foundation;
import AVFoundation;

extension UIView {
    var parentViewController: UIViewController? {
        var parentResponder: UIResponder? = self
        while parentResponder != nil {
            parentResponder = parentResponder!.next
            if let viewController = parentResponder as? UIViewController {
                return viewController
            }
        }
        return nil
    }
}



@objc(paytab)
class paytab : CDVPlugin {
    
    var initialSetupViewController: PTFWInitialSetupViewController!
    
    var cmd : CDVInvokedUrlCommand?
    
    func add(_ command: CDVInvokedUrlCommand) {
        cmd = command
        let pluginResult = CDVPluginResult(
            status: CDVCommandStatus_ERROR
        )
        
        //        NSNumber *param1 = [[command.arguments objectAtIndex:0] valueForKey:@"param1"]
        //        NSNumber *param2 = [[command.arguments objectAtIndex:0] valueForKey:@"param2"]
        
        let data = command.arguments[0];
        
        
        
        
        
        if let view = self.webView.superview {
            
            if let vc = view.parentViewController {
                
                if let dic = data as? NSDictionary {
                    
                    self.initiateSDK(dic: dic)
                }
                
                view.addSubview(initialSetupViewController.view)
                vc.addChildViewController(initialSetupViewController)
                
                initialSetupViewController.didMove(toParentViewController: vc)
            }
        }
        
        
    }
    
    private func initiateSDK(dic: NSDictionary) {
        //        self.launcherView.endEditing(true)
        let bundle = Bundle(url: Bundle.main.url(forResource: ApplicationResources.kFrameworkResourcesBundle, withExtension: "bundle")!)
        
        self.initialSetupViewController = PTFWInitialSetupViewController.init(
            nibName: ApplicationXIBs.kPTFWInitialSetupView,
            bundle: bundle,
            andWithViewFrame: self.webView.frame,
            andWithAmount: dic["amount"] as! Float,
            andWithCustomerTitle: dic["transactionTitle"] as! String,
            andWithCurrencyCode:"SAR",
            andWithTaxAmount: 0.0,
            andWithSDKLanguage: ((dic["language"] as! String) == "English") ? "en" : "ar" ,
            andWithShippingAddress: dic["address_billing"] as! String,
            andWithShippingCity: dic["city_billing"] as! String,
            andWithShippingCountry: dic["country_billing"] as! String,
            andWithShippingState: "Saudi Arabia",
            andWithShippingZIPCode: "11543",
            andWithBillingAddress: dic["address_billing"] as! String,
            andWithBillingCity: dic["city_billing"] as! String,
            andWithBillingCountry: dic["country_billing"] as! String,
            andWithBillingState: "Saudi Arabia",
            andWithBillingZIPCode: "11543",
            andWithOrderID: dic["order_id"] as! String,
            andWithPhoneNumber: dic["customer_phone"] as! String,
            andWithCustomerEmail: dic["customer_email"] as! String,
            andWithCustomerPassword: "rVoPaGMCaL",
            andIsTokenization: false,
            andIsExistingCustomer: false,
            andWithPayTabsToken: "s7oeTQxwJGpNGjFymzOSLWafapeIzBG1",
            andWithMerchantEmail: "waseem@bahjahcards.com",
            andWithMerchantSecretKey: "FKvdaXWcErgZKgrcl4QoLBMDy4lvnyf2iLgsIcEnDVnncOzxTqVF1IiANE5aclcv6sXPECciyAFKofiiGeXHWfiKTnU6I7BPzzEe",
            andWithRequestTimeoutSeconds: 200,
            
            andWithAssigneeCode: "SDK")
        
        weak var weakSelf = self
        weakSelf?.initialSetupViewController.didReceiveBackButtonCallback = {
            weakSelf?.handleBackButtonTapEvent()
        }
        //        self.initialSetupViewController.didReceiveBackButtonCallback = {
        //            weakSelf?.handleBackButtonTapEvent()
        //        }
        
        self.initialSetupViewController.didReceiveFinishTransactionCallback = {(responseCode, result, transactionID, tokenizedCustomerEmail, tokenizedCustomerPassword, token, transactionState) in
            
            
            print(transactionID);
            print(responseCode);
            
            
            
            
            
            
            
            //            object.put("response_code", pt_response_code);
            //            object.put("transaction_id", pt_transaction_id);
            
            var pluginResult = CDVPluginResult(
                status: CDVCommandStatus_OK,
                messageAs: ["response_code":String(responseCode), "transaction_id": String(transactionID)]
            )
            self.commandDelegate!.send(
                pluginResult,
                callbackId: self.cmd?.callbackId
                
            )
        }
    }
    
    
    // MARK: Close SDK Event
    private func handleBackButtonTapEvent() {
        
        if let view = self.webView.superview {
            
            if let vc = view.parentViewController {
                
                self.initialSetupViewController.willMove(toParentViewController: vc)
                self.initialSetupViewController.view.removeFromSuperview()
                self.initialSetupViewController.removeFromParentViewController()
                
                var pluginResult = CDVPluginResult(
                    status: CDVCommandStatus_OK,
                    messageAs: ["response_code":"", "transaction_id": ""]
                )
                self.commandDelegate!.send(
                    pluginResult,
                    callbackId: self.cmd?.callbackId
                    
                )
            }
        }
        
    }
}
