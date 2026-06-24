import { useEffect, useState } from "react";
import api from "../services/api";

function PatientViewPage() {

    const [queueStatus, setQueueStatus] = useState({
        currentToken: 0,
        waitingCount: 0
    });

    const fetchQueueStatus = async () => {
        try {
            const response = await api.get("/patients/queue-status");
            setQueueStatus(response.data);
        } catch (error) {
            console.error(error);
        }
    };

    useEffect(() => {

        fetchQueueStatus();

        const interval = setInterval(() => {
            fetchQueueStatus();
        }, 2000);

        return () => clearInterval(interval);

    }, []);

    return (
        <div>
            <h1>Patient Waiting Room</h1>

            <h2>
                Now Serving: {queueStatus.currentToken}
            </h2>

            <h2>
                Waiting Patients: {queueStatus.waitingCount}
            </h2>

            <h2>
                Estimated Wait: {
                    queueStatus.waitingCount *
                    (queueStatus.averageConsultationTime || 5)
                } mins
            </h2>
        </div>
    );
}

export default PatientViewPage;