# hospital_manager
complete system to manage hospitals

Technologies : Java, Spring , SpringBoot, Postgresql, Kafka, Docker, AWS, Kubernetes 

Services
	UserService
		api endpoints : 
			signInUser
			signUpUser
			
			
	ClinicService
		Api endpoints : 
			Manage patients (Create, Update, Delete, view)
			Manage Doctors (Create, Update, Delete, view)
Manage Clincs (Create/Update clinic,view, Assign/Update/Delete doctors, patients, start/update/end ongoing clinics)
Update current clinic details

	PharmacyService
		API endpoints :
			Manage inventory (add, update, modify, delete, view)

	NotificationService
		API endpoints :
			Send sms (channeling info, doctor status)
			Send email (channeling info, payment info)
	PaymentService
		API endpoints :
			Payments with providers

User Stories

UserService

User can sign into the system.
There will be predefined admin users (1 per each branch)
Admin user can add more admin users
Admin user can add Staff
Staff enum ( Admin, Receptionist, Doctor, Patient, Nurse, Pharmacist)

Receptionist can add and modify patients (need the audit logs) – CQRS
Pharmacist can add and update pharmacy items
Patients can 
	see the history
	online channeling,	
	payments, 
	submit prescriptions and order medicines
Nurse
	Update doctor status
	Update current patient details
	See registered patients the nurse assigned clinic
Doctor
	Assigned patients history
	Clinic history
	Upcoming clinic details
