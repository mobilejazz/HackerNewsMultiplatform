//
//  ItemDetailViewController.swift
//  HackerNews Multiplatform
//
//  Created by Joan Martin on 29/08/2019.
//  Copyright © 2019 Mobile Jazz S.L. All rights reserved.
//

import UIKit
import HackerNewsCore

class ItemDetailViewController: UITableViewController, ItemDetailPresenterView {
    
    private lazy var presenter : ItemDetailPresenter = ItemDetailDefaultPresenter(view: self)
    
    // The item Id that will be passed by the previous controlelr
    var itemId : Int32?
    
    private var item : Item! {
        didSet { self.title = item.title }
    }
    private var kids: [Item] = []
    
    // MARK: ItemDetailPresenterView
    
    func onEventDisplay(item: Item, kids: [Item]) {
        self.item = item
        self.kids = kids
        self.tableView.reloadData()
    }
    
    // MARK: ViewController

    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        guard let itemId = itemId else { fatalError("Missing item id") }
        presenter.onAppearWith(itemId: itemId)
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        return 2
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        switch section {
        case 0:
            return item == nil ? 0 : 1
        case 1:
            return kids.count
        default:
            return 0
        }
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "ItemCell", for: indexPath) as! ItemCell

        switch indexPath.section {
        case 0:
            cell.titleLabel.text = item.title
            cell.byLabel.text = "by @\(item.by)"
            cell.contentLabel.attributedText = item.attributtedText()
        case 1:
            let comment = kids[indexPath.row]
            cell.titleLabel.text = comment.title
            cell.byLabel.text = "by @\(comment.by)"
            cell.contentLabel.attributedText = comment.attributtedText()
        default:
            break
        }

        return cell
    }
}
