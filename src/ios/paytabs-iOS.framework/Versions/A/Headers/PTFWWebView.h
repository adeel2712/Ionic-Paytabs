//
//  PTFWWebView.h
//  paytabs-iOS
//
//  Created by PayTabs LLC on 10/23/17.
//  Copyright © 2017 PayTabs LLC. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface PTFWWebView : UIView<UITextFieldDelegate>

#pragma mark - IBOutlets
@property (weak, nonatomic, nullable) IBOutlet UIWebView *webView;

#pragma mark - Callbacks
@property (nonatomic, copy, nullable) void(^didPressBackButtonCallback)(void);

@end

