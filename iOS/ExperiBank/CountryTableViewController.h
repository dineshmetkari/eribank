//
//  CountryTableViewController.h
//  ExperiBank
//
//  Created by TechFlitter on 07/01/13.
//  Copyright (c) 2013 Experitest. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface CountryTableViewController : UITableViewController
{
    NSArray *countriesArray;
}

@property(nonatomic, assign) UITextField *countryTextField;

@end
