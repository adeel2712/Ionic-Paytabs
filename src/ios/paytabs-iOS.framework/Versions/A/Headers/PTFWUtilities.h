//
//  PTFWUtilities.h
//  paytabs-iOS
//
//  Created by Humayun Sohail on 10/11/17.
//  Copyright © 2017 PayTabs LLC. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface PTFWUtilities : NSObject

#pragma mark - Shared Instance
+ (PTFWUtilities *)sharedInstance;

#pragma mark - Public Methods
#pragma mark - Countries
- (BOOL)isCountryNameValidWith: (NSString *)countryName andWithBundle: (NSBundle *)bundle;

#pragma mark - Currencies
- (BOOL)isCurrencyCodeValidWith: (NSString *)currencyCode andWithBundle: (NSBundle *)bundle;

#pragma mark - Reason Code
- (NSString *)getReasonWithReasonCode: (int)reasonCode andWithBundle: (NSBundle *)bundle;

@end
