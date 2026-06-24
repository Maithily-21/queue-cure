# Queue Cure

A real-time clinic queue management system built to reduce patient waiting uncertainty and streamline receptionist workflow.

## Problem Statement

Many clinics still rely on paper tokens and manual queue tracking. Patients have little visibility into waiting times, while receptionists manage queues manually.

Queue Cure digitizes this workflow through a simple queue management system.

## Features

- Add patient to queue
- Auto-generate token numbers
- Call next patient
- Track current token in progress
- View queue status
- Calculate waiting count

## Tech Stack

### Backend
- Java
- Spring Boot
- Spring Data JPA
- H2 Database

### Frontend
- React (In Progress)

## API Endpoints

### Add Patient

POST /patients

### Get Patients

GET /patients

### Call Next Patient

POST /patients/call-next

### Queue Status

GET /patients/queue-status

## Future Enhancements

- Real-time updates using WebSockets
- Appointment scheduling
- AI-powered queue prediction
- Multi-doctor support
