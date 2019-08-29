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
    
    var item : Item! {
        didSet {
            self.title = item.title
        }
    }
    
    var comments: [Item] = []
    
    // MARK: ItemDetailPresenterView
    
    func onEventDisplay(comments: [Item]) {
        self.comments = comments
        
        self.tableView.refreshControl?.endRefreshing()
        DispatchQueue.main.async {
            self.tableView.reloadData()
        }
    }
    
    // MARK: ViewController

    override func viewDidLoad() {
        super.viewDidLoad()
        
        tableView.refreshControl = UIRefreshControl()
        tableView.refreshControl?.addTarget(self, action: #selector(pullToRefresh(sender:)), for: .valueChanged)
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        presenter.onAppearWith(item: item)
    }
    
    // Using @objc as this method is referenced inside a #selector call
    @objc func pullToRefresh(sender: UIRefreshControl) {
        presenter.onActionRefresh(item: item)
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
            return comments.count
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
            let comment = comments[indexPath.row]
            cell.titleLabel.text = comment.title
            cell.byLabel.text = "by @\(comment.by)"
            cell.contentLabel.attributedText = comment.attributtedText()
        default:
            break
        }

        return cell
    }
}
