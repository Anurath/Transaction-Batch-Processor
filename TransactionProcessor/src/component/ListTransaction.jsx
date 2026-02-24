import { useEffect, useState } from "react"
import "./ListTransaction.css";

export default function ListTransaction() {

  const [transactions, setTransaction] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/transaction").then((response) => response.json()).then((data) => {
      console.log(data);
      setTransaction(data);
    })
  }, []);

  return (
    <div className="listTransactionContainer">
      <h2>Transaction List</h2>

      <table className="mainTable">
        <thead>
          <tr>
            <th>ID</th>
            <th>Location</th>
            <th>Status</th>
            <th>Process Status</th>
            <th>Balance</th>
            <th>Transaction Amount</th>
            <th>Receiver ID</th>
            <th>Tax</th>
            <th>Created At</th>
          </tr>
        </thead>

        <tbody className="tBody">
          {transactions.map((t) => (
            <tr key={t.transactionId}>
              <td>{t.transactionId}</td>
              <td>{t.location}</td>
              <td
                style={{
                  color:
                    t.status?.trim().toLowerCase() === "success"
                      ? "green"
                      : t.status?.trim().toLowerCase() === "fail"
                        ? "red"
                        : "black",
                  fontWeight: "bold",
                }}
              >
                {t.status}
              </td>
              <td>{t.processStatus}</td>
              <td>{t.balance}</td>
              <td>{t.transactionAmount}</td>
              <td>{t.receiverId}</td>
              <td>{t.tax}</td>
              <td>{new Date(t.createdAt).toLocaleString()}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}