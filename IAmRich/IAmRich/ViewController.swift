//
//  ViewController.swift
//  IAmRich
//
//  Created by Mahesh, Harshitha on 8/27/24.
//

import UIKit

class ViewController: UIViewController {
    var richVisible = true
    @IBOutlet var richLabel: UILabel!
    override func viewDidLoad() {
    super.viewDidLoad()
    updateVisibility()
    }

    fileprivate func updateVisibility() {
        if (richVisible == true) {
            view.backgroundColor = .yellow
            richLabel.text = "I am Rich! :D"
        }
        else
        {
            view.backgroundColor = .brown
            richLabel.text = "I am Poor! :("
        }
    }
    
    @IBAction func invisibleSwitch(_ sender: Any) {
        richVisible.toggle()
        updateVisibility()
    }
    
}

