//
//  CountryTableViewController.m
//  ExperiBank
//
//  Created by TechFlitter on 07/01/13.
//  Copyright (c) 2013 Experitest. All rights reserved.
//

#import "CountryTableViewController.h"

@interface CountryTableViewController ()

@end

@implementation CountryTableViewController

@synthesize countryTextField;

- (id)initWithStyle:(UITableViewStyle)style
{
    self = [super initWithStyle:style];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    [self setAccessibilityLabel:@"countryList"];
    countriesArray = [NSArray arrayWithObjects:@"India", @"USA", @"Iceland", @"Greenland", @"Switzerland", @"Norway", @"New Zealand", @"Greece", @"Italy", @"Ireland", @"China", @"Japan", @"France", @"Russia", @"Australlia", @"Canada", nil];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

#pragma mark - Table view data source

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    return [countriesArray count];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    static NSString *CellIdentifier = @"Cell";
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier];
    if (cell == nil) {
        cell = [[UITableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:CellIdentifier];
    }
    
    cell.textLabel.text = [countriesArray objectAtIndex:indexPath.row];
    
    return cell;
}

#pragma mark - Table view delegate

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    countryTextField.text = [countriesArray objectAtIndex:indexPath.row];
    [self dismissViewControllerAnimated:YES completion:nil];
}

@end
