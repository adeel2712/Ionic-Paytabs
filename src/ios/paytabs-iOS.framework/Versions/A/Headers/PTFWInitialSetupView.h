//
//  PTFWInitialSetupView.h
//  paytabs-iOS
//
//  Created by Humayun Sohail on 10/5/17.
//  Copyright © 2017 PayTabs LLC. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface PTFWInitialSetupView : UIView

#pragma mark - IBOutlets
@property (weak, nonatomic, nullable) IBOutlet UILabel *messageLabel;
@property (weak, nonatomic, nullable) IBOutlet UILabel *pleaseWaitLabel;

@property (weak, nonatomic, nullable) IBOutlet UIActivityIndicatorView *activityIndicatorView;

#pragma mark - Callbacks
@property (nonatomic, copy, nullable) void(^didPressBackButtonCallback)(void);

@end

