//
//  ViewController.swift
//  IP03-Destiny
//
//  Created by Mahesh, Harshitha on 9/12/24.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet var firstOption: UIButton!
    @IBOutlet var secondOption: UIButton!
    @IBOutlet var currentRoom: UILabel!
    var game = Destiny()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        setUpRooms()
    }
    
    func setUpRooms() {
        currentRoom.text = game.storyText()
        firstOption.setTitle(game.choice1(), for: .normal)
        secondOption.setTitle(game.choice2(), for: .normal)
    }
    
    @IBAction func nextEvent(_ sender: UIButton) {
        if sender == firstOption {
            if !(game.choice1Index() < 0) {
                game.currentStory = game.choice1Index()
                setUpRooms()
            }
        }
        else {
                game.currentStory = game.choice2Index()
                setUpRooms()
        }
    }
        
    
    
    
}

