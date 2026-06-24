import { useState , useEffect } from "react";
import api from "../services/api";

function ReceptionistPage() {

    const [name, setName] = useState("");
    const [queueStatus, setQueueStatus] = useState({
            currentToken: 0,
            waitingCount: 0
    });
    const [avgTime, setAvgTime] = useState(5);
    const [patients, setPatients] = useState([]);
    const addPatient = async () => {

        await api.post("/patients", {
            patientName: name
        });

        setName("");

        fetchPatients();
        fetchQueueStatus();
    };
    const callNext = async () => {

        await api.post("/patients/call-next");

        fetchPatients();
        fetchQueueStatus();
    };

    const fetchQueueStatus = async () => {

        const response =
            await api.get("/patients/queue-status");

        setQueueStatus(response.data);
    };

    const fetchPatients = async () => {
        const response = await api.get("/patients");
        setPatients(response.data);
    };
    useEffect(() => {
        fetchQueueStatus();
        fetchPatients();
    }, []);
    return (
        <div className="dashboard">
            <h1>Receptionist Dashboard</h1>

            <div className="stats">
                <h3>Current Token: {queueStatus.currentToken}</h3>
                <h3>Waiting Count: {queueStatus.waitingCount}</h3>
            </div>
            <div className="form-section">
            <input
                type="number"
                value={avgTime}
                onChange={(e) => setAvgTime(e.target.value)}
                placeholder="Avg Consultation Time"
            />
            <input
                value={name}
                onChange={(e) => setName(e.target.value)}
                placeholder="Patient Name"
            />

            <button onClick={addPatient}>
                Add Patient
            </button>

            <button onClick={callNext}>
                Call Next
            </button>
            </div>
            <h2>Patients Queue</h2>

            <table border="1">
                <thead>
                <tr>
                    <th>Token</th>
                    <th>Name</th>
                    <th>Status</th>
                </tr>
                </thead>

                <tbody>
                {patients.map((patient) => (
                    <tr key={patient.id}>
                        <td>{patient.tokenNumber}</td>
                        <td>{patient.patientName}</td>
                        <td>{patient.status}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
}

export default ReceptionistPage;