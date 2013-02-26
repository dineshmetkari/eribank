//
//  MakePaymentViewController.h
//  ExperiBank
//
//  Created by TechFlitter on 31/12/12.
//  Copyright (c) 2012 Experitest. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface MakePaymentViewController : UIViewController <UITextFieldDelegate>
{
    IBOutlet UITextField *phoneTextField;
    IBOutlet UITextField *nameTextField;
    IBOutlet UITextField *countryTextField;
    IBOutlet UIButton *countryButton;
    IBOutlet UIButton *sendPaymentButton, *cancelButton;
    IBOutlet UITextField *amountTextField;
}
@end
