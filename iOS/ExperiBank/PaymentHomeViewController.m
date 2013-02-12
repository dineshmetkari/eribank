//
//  PaymentHomeViewController.m
//  ExperiBank
//
//  Created by TechFlitter on 01/01/13.
//  Copyright (c) 2013 Experitest. All rights reserved.
//

#import "PaymentHomeViewController.h"
#import "MakePaymentViewController.h"

@interface PaymentHomeViewController ()

@end

@implementation PaymentHomeViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    
    [self.view setAccessibilityLabel:@"paymentHomeView"];
    [makePaymentButton setAccessibilityLabel:@"makePaymentButton"];
    [balanceWebView setAccessibilityLabel:@"balanceWebView"];
    [logoutButton setAccessibilityLabel:@"logoutButton"];
}

- (void)viewDidUnload
{
    balanceWebView = nil;
    [super viewDidUnload];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)viewWillAppear:(BOOL)animated
{
    NSString *html = [NSString stringWithFormat:@"<html><body><h1 align='center'>Your balance is: <br/> %02.02f$ </h1></body></html>",
                      [[NSUserDefaults standardUserDefaults] floatForKey:@"Balance"]];
    [balanceWebView loadHTMLString:html baseURL:nil];
}

- (IBAction)makePaymentPressed:(id)sender
{
    MakePaymentViewController *makePaymentViewController = [[MakePaymentViewController alloc]
                                                            initWithNibName:@"MakePaymentViewController" bundle:nil];
    [self presentViewController:makePaymentViewController animated:YES completion:nil];
}

- (IBAction)singoutPressed:(id)sender
{
    [self dismissViewControllerAnimated:YES completion:nil];
}

@end
