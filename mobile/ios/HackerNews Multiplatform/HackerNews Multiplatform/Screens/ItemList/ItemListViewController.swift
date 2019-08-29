//
//  ItemListViewController.swift
//  HackerNews Multiplatform
//
//  Created by Joan Martin on 28/08/2019.
//  Copyright © 2019 Mobile Jazz S.L. All rights reserved.
//

import UIKit
import HackerNewsCore

class ItemListViewController: UITableViewController, ItemListPresenterView {
    
    private lazy var presenter : ItemListPresenter = ItemListDefaultPresenter(view: self)
    private var items : [Item] = []
    
    // MARK: ItemListPresenterView
    
    func onEventDisplay(items: [Item]) {
        self.items = items
        
        self.tableView.refreshControl?.endRefreshing()
        DispatchQueue.main.async {
            self.tableView.reloadData()
        }
    }
    
    func onEventNavigateTo(item: Item) {
        performSegue(withIdentifier: "segue.item.detail", sender: item)
    }
    
    // MARK: ViewController
    
    override func viewDidLoad() {
        super.viewDidLoad()
        tableView.refreshControl = UIRefreshControl()
        tableView.refreshControl?.addTarget(self, action: #selector(pullToRefresh(sender:)), for: .valueChanged)
    }
    
    // Using @objc as this method is referenced inside a #selector call
    @objc func pullToRefresh(sender: UIRefreshControl) {
        presenter.onActionRefresh()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        presenter.onAppear()
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return items.count
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "ItemCell", for: indexPath) as! ItemCell
        
        let item = items[indexPath.row]
        
        cell.titleLabel.text = item.title
        cell.byLabel.text = "by @\(item.by)"
        cell.contentLabel.attributedText = item.attributtedText()
        
        return cell
    }
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let item = items[indexPath.row]
        presenter.onActionDidSelect(item: item)
    }

    // MARK: - Navigation
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "segue.item.detail" {
            guard let item = sender as? Item else { fatalError() }
            guard let vc = segue.destination as? ItemDetailViewController else { fatalError() }
            vc.item = item
        }
    }
}
