# ExpenseTracker
 Application to keep track of your expenses. 
## Installation
 Fork the repository and then clone it.<br>
 After you clone the repository the default remote is `origin` and it refers to your fork on Github. You must keep track of the changes made to the original repository by setting up another remote named `upstream`.

1. Open terminal in your repository and type <br>`git remote add origin https://github.com/YOUR_USERNAME/ExpenseTracker.git`
2. Type <br>` git remote add upstream https://github.com/vyankatesh24/ExpenseTracker.git `<br>to add upstream.
3. Type ` git remote -v ` and you should see <br>
```
   origin  https://github.com/YOUR_USERNAME/ExpenseTracker.git (fetch)
   origin  https://github.com/YOUR_USERNAME/ExpenseTracker.git (push) 
   upstream        https://github.com/vyankatesh24/ExpenseTracker.git (fetch)
   upstream        https://github.com/vyankatesh24/ExpenseTracker.git (push)
```

To now update your local copy type <br> `git fetch upstream` <br> `git merge upstream/master` <br> `git push`

## Steps for creating a Pull Request

1. Checkout to the master branch `git checkout master`
2. Sync `git pull`
3. Start a new branch with a suitable name `git checkout -b branch_name`
4. Develop a new feature or solve an existing issue 
5. Add the changed files `git add file_name`
6. Commit with a suitable message `git commit -m " Changes made "`
7. Push `git push origin branch_name`
8. Go to the Github Repository and create a pull request


## Communication Channel
[https://groups.google.com/forum/#!forum/expense-tracker/join](Google Group)
