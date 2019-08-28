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
    
    func onEventDisplay(items: [Item]) {
        self.items = items
        self.tableView.reloadData()
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
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
        let cell = tableView.dequeueReusableCell(withIdentifier: "reuseIdentifier", for: indexPath)
        let item = items[indexPath.row]
        cell.textLabel?.text = item.title
        return cell
    }

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
