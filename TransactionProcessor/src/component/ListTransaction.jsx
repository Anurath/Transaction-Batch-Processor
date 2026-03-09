import { useEffect, useState, useRef } from "react";
import "./ListTransaction.css";

export default function ListTransaction() {
  const [transactions, setTransaction] = useState([]);
  const [audioStatus, setAudioStatus] = useState("idle");
  const audioRef = useRef(null);
  const audioUrlRef = useRef(null);

  useEffect(() => {
    fetch("http://localhost:8084/transaction")
      .then((res) => res.json())
      .then((data) => setTransaction(data));
  }, []);

  useEffect(() => {
  const interval = setInterval(() => {
    fetch("http://localhost:8081/tsnprocessor")
      .then((res) => res.json())
      .catch((err) => console.error("Processor error:", err));
  }, 3000);

  return () => clearInterval(interval); 
}, []);

  const handleInsights = async () => {
    if (audioStatus === "playing") {
      audioRef.current.pause();
      audioRef.current.currentTime = 0;
      setAudioStatus("idle");
      return;
    }
    if (audioUrlRef.current) {
      URL.revokeObjectURL(audioUrlRef.current);
      audioUrlRef.current = null;
    }
    setAudioStatus("loading");
    try {
      const response = await fetch("http://localhost:8085/wise/sharvam");
      if (!response.ok) throw new Error("API failed");
      const blob = await response.blob();
      const url = URL.createObjectURL(new Blob([blob], { type: "audio/wav" }));
      audioUrlRef.current = url;
      audioRef.current.src = url;
      audioRef.current.onended = () => setAudioStatus("idle");
      await audioRef.current.play();
      setAudioStatus("playing");
    } catch (err) {
      setAudioStatus("error");
      setTimeout(() => setAudioStatus("idle"), 2000);
    }
  };

  const statusMap = {
    idle:    { label: "🔍 Give Insights", cls: "btn-idle" },
    loading: { label: "⏳ Fetching...",   cls: "btn-loading" },
    playing: { label: "⏹ Stop Audio",    cls: "btn-playing" },
    error:   { label: "✕ Error. Retry?", cls: "btn-error" },
  };

  return (
    <div className="page">
      <div className="header">
        <div>
          <h2 className="title">Transaction <span>List</span></h2>
          <p className="subtitle">LIVE FEED — ALL TRANSACTIONS</p>
        </div>
        <button
          onClick={handleInsights}
          disabled={audioStatus === "loading"}
          className={`insight-btn ${statusMap[audioStatus].cls}`}
        >
          {statusMap[audioStatus].label}
        </button>
      </div>

      <audio ref={audioRef} style={{ display: "none" }} />

      <div className="table-wrapper">
        <table className="txn-table">
          <thead>
            <tr>
              <th>ID</th>
              <th>Location</th>
              <th>Status</th>
              <th>Process Status</th>
              <th>Balance</th>
              <th>Amount</th>
              <th>Receiver ID</th>
              <th>Tax</th>
              <th>Created At</th>
            </tr>
          </thead>
          <tbody>
            {transactions.map((t) => (
              <tr key={t.transactionId}>
                <td>{t.transactionId}</td>
                <td>{t.location}</td>
                <td>
                  <span className={`badge ${
                    t.status === "SUCCESS" ? "badge-success" :
                    t.status === "FAILED"  ? "badge-failed"  : "badge-pending"
                  }`}>
                    {t.status}
                  </span>
                </td>
                <td>{t.processStatus === "C" ? "Complete" : "Not Complete"}</td>
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
    </div>
  );
}