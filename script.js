let transactions = [];
let income = 0;
let expenses = 0;

function showAddTransactionForm() {
    document.getElementById('addTransactionForm').style.display = 'block';
    document.getElementById('transactionsList').style.display = 'none';
    document.getElementById('summary').style.display = 'none';
}

function hideAddTransactionForm() {
    document.getElementById('addTransactionForm').style.display = 'none';
    document.getElementById('transactionsList').style.display = 'none';
    document.getElementById('summary').style.display = 'none';
}

function addTransaction(event) {
    event.preventDefault();

    const type = document.getElementById('type').value;
    const amount = parseFloat(document.getElementById('amount').value);
    const description = document.getElementById('description').value;

    if (type === "Income" || type === "Expense") {
        const transaction = { type, amount, description };
        transactions.push(transaction);

        // Update income or expense totals
        if (type === "Income") {
            income += amount;
        } else if (type === "Expense") {
            expenses += amount;
        }

        // Reset form and hide it
        document.getElementById('type').value = '';
        document.getElementById('amount').value = '';
        document.getElementById('description').value = '';

        // Update UI
        viewTransactions();
        viewSummary();
        hideAddTransactionForm();
    } else {
        alert('Invalid transaction type. Please use "Income" or "Expense".');
    }
}

function viewTransactions() {
    document.getElementById('addTransactionForm').style.display = 'none';
    document.getElementById('transactionsList').style.display = 'block';
    document.getElementById('summary').style.display = 'none';

    const transactionList = document.getElementById('transactionList');
    transactionList.innerHTML = '';

    transactions.forEach(transaction => {
        const li = document.createElement('li');
        li.textContent = `${transaction.type}: ${transaction.description} - $${transaction.amount.toFixed(2)}`;
        transactionList.appendChild(li);
    });
}

function viewSummary() {
    document.getElementById('addTransactionForm').style.display = 'none';
    document.getElementById('transactionsList').style.display = 'none';
    document.getElementById('summary').style.display = 'block';

    document.getElementById('income').textContent = income.toFixed(2);
    document.getElementById('expenses').textContent = expenses.toFixed(2);
    document.getElementById('balance').textContent = (income - expenses).toFixed(2);
}
