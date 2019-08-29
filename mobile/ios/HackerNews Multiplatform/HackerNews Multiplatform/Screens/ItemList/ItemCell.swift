//
//  ItemCell.swift
//  HackerNews Multiplatform
//
//  Created by Joan Martin on 29/08/2019.
//  Copyright © 2019 Mobile Jazz S.L. All rights reserved.
//

import UIKit

import HackerNewsCore

extension Item {
    func attributtedText() -> NSAttributedString? {
        guard let data = text.data(using: String.Encoding.utf16, allowLossyConversion: false) else { return nil }
        guard let html = try? NSMutableAttributedString(data: data,
                                                        options: [NSAttributedString.DocumentReadingOptionKey.documentType: NSAttributedString.DocumentType.html],
                                                        documentAttributes: nil) else { return nil }
        return html
    }
}

class ItemCell: UITableViewCell {
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var byLabel: UILabel!
    @IBOutlet weak var contentLabel: UILabel!
}
