//
//  ViewController.swift
//  IP02-WhackAMole
//
//  Created by Mahesh, Harshitha on 9/4/24.
//

import UIKit

class ViewController: UIViewController {
    
    var timer = Timer()
    
    var screenWidth  = 0
    var screenHeight = 0
    
    var score = 0
    
    var firstTime : Bool = false
    
    var buttonWid = 100
    var buttonHeigh = 30
    var mole = UIButton()
    
    var lblHorizontal = UILabel()
    var lblVertical   = UILabel()
    
    @IBOutlet var scoreLabel: UILabel!
    @IBOutlet var backgroundLabel: UILabel!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Do any additional setup after loading the view.
        
        let screenBounds: CGRect = UIScreen.main.bounds
        screenWidth  = Int(screenBounds.width)
        screenHeight = Int(screenBounds.height)
        
        //Create Mole Button
        mole.frame = CGRect(x: 70, y: 100, width: buttonWid, height: buttonHeigh)
        mole.backgroundColor = UIColor.lightGray
        mole.addTarget(self, action: #selector(selected(_:)), for: .touchUpInside)
        mole.setTitle("Tap to start", for: .normal)
        view.addSubview(mole)
        
        //Create Score label
        scoreLabel.frame = CGRect(x: 35, y: 20, width: 50, height: screenHeight * 1/10)
        scoreLabel.text = "0"
        
        view.addSubview(scoreLabel)
        
        backgroundLabel.text = ""
        backgroundLabel.backgroundColor = UIColor.green
        self.view = view
        
        timer = Timer.scheduledTimer(timeInterval: 2.0, target: self, selector: #selector(timerFunction), userInfo: nil, repeats: true)
    }
    
    func randomChange() {
        mole.isHidden = true
        var randomIntX = Int.random(in: 70..<screenWidth - 150)
        var randomIntY = Int.random(in: 80..<screenHeight - 130)
        mole.frame = CGRect(x: randomIntX, y: randomIntY, width: buttonWid, height: buttonHeigh)
        mole.isHidden = false
        timer = Timer.scheduledTimer(timeInterval: 2.0, target: self, selector: #selector(timerFunction), userInfo: nil, repeats: true)
    }
    
    @objc func selected(_ sender : UIButton) {
        if !(firstTime) {
            firstTime = true
            mole.setTitle("Whack me!", for: .normal)
        }
        timer.invalidate()
        score += 1
        scoreLabel.text = "\(score)"
        randomChange()
    }
    
    @objc func timerFunction() {
        score -= 1
        scoreLabel.text = "\(score)"
        timer.invalidate()
        randomChange()
    }
}


    
    
    
    


