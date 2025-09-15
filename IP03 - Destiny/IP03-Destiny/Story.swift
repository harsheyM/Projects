//
//  Story.swift
//  IP03-Destiny
//
//  Created by Mahesh, Harshitha on 9/12/24.
//

import Foundation
struct Story {
    let storyText: String
    let choice1: String
    let choice1Index: Int
    let choice2: String
    let choice2Index: Int
    
    init(storyText: String, choice1: String, choice1Index: Int, choice2: String, choice2Index: Int) {
        self.storyText = storyText
        self.choice1 = choice1
        self.choice1Index = choice1Index
        self.choice2 = choice2
        self.choice2Index = choice2Index
    }
}

