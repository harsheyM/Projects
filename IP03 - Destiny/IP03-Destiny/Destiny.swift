//
//  Destiny.swift
//  IP03-Destiny
//
//  Created by Mahesh, Harshitha on 9/12/24.
//

import Foundation
class Destiny {
    var currentStory : Int = 0
    
    let stories = [
    Story(storyText: "You are walking across the park when you suddenly fall in a hole. In the hole you are stuck in a room with a drink and a tart.", choice1: "Eat the strawberry tart", choice1Index: 1, choice2: "Drink the blueberry drink", choice2Index: 2),
    
    //Select choice 1 -> Current Index 1
    Story(storyText: "After the drink, you find yourself shrinking and are very small. There's a small door and another tart", choice1: "You decide to walk outside the door", choice1Index: 3, choice2: "You decide to try the strawberry tart", choice2Index: 2),
    
    //Select choice 2 -> Current Index 2
    Story(storyText: "You've become very tall and have broken through the ceiling. Outside you can see a forest that extends for miles. The height comes just beneath your waist. A butterfly is flying by your face.", choice1: "Follow the butterfly", choice1Index: 4, choice2: "Start crying and throwing trees", choice2Index: 5),
    
    //Select choice 3 -> Current Index 3
    Story(storyText: "You find yourself at a tea party. There are people dancing on the table, passed out in the bushes, and stacking treats in their mouth", choice1: "Join the party!", choice1Index: 6, choice2: "Run away into the forest", choice2Index: 7),
    
    //Select choice 4 -> Current Index 2
    
    //Select choice 5 -> Current Index 4
    Story(storyText: "The butterfly leads you to a small castle seemingly stained red. You hear the screeching shrieks and the elegance of harps", choice1: "Knock on the gate", choice1Index: 8, choice2: "Run away into the forest", choice2Index: 7),
    
    //Select choice 6 -> Current Index 5
    Story(storyText: "Your tears begin forming a river and you hear a fanfare of trumpets coming closer", choice1: "Run away into the forest", choice1Index: 7, choice2: "Wait to see who's coming", choice2Index: 9),
    
    //Select choice 7 -> Current Index 6
    Story(storyText: "You enter the party and start having fun! But, you hear the fanfare of trumpets coming closer, the party goers are telling you to escape with them", choice1: "Wait to see who's coming", choice1Index: 9, choice2: "Escape with other party goers", choice2Index: 10),
    
    //Select choice 8 -> Current Index 7 (Bad ending #1)
    Story(storyText: "You keep running and running, but you lose your bearings. You're lost forever", choice1: "The End", choice1Index: -1, choice2: "Try Again?", choice2Index: 0),
    
    //Select choice 9 -> Current Index 8
    Story(storyText: "The doors open and you see two doors, one tall and one short", choice1: "Open the tall door", choice1Index: 9, choice2: "Open the short door", choice2Index: 11),
    
    //Select choice 10 -> Current Index 9
    Story(storyText: "You see an angry queen! She gives you a choice between a tart and a key. If you choose the wrong one she promises punishment", choice1: "You eat the tart", choice1Index: 10, choice2: "You choose the key", choice2Index: 11),
    
    //Select choice 11 -> Current Index 10 (Bad ending #2)
    Story(storyText: "You find yourself chained to the chair hanging above lava. You see a dragon approaching you. You come to a gruesome end!", choice1: "The End", choice1Index: -1, choice2: "Try Again?", choice2Index: 0),
    
    //Select choice 12 -> Current Index 11
    Story(storyText: "You have a key, but how do you use it? The Queen takes you to an abandoned castle. Choose which door you open carefully!", choice1: "The Iron Clad Door", choice1Index: 10, choice2: "The Muddy Door", choice2Index: 12),
    
    //Select choice 13 -> Current Index 12 (Good ending)
    Story(storyText: "You walk through the door and huzzah! You see the park! Next time be more careful when walking!", choice1: "The End", choice1Index: -1, choice2: "Try Again?", choice2Index: 0)
    
    //Select choice 14 -> Current Index 0 (Play again)
    
    ]
    
    func storyText() -> String {
        return stories[currentStory].storyText
    }
    func choice1() -> String {
        return stories[currentStory].choice1
    }
    func choice1Index() -> Int {
        return stories[currentStory].choice1Index
    }
    func choice2() -> String {
        return stories[currentStory].choice2
    }
    func choice2Index() -> Int {
        return stories[currentStory].choice2Index
    }
    
}
