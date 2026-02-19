const express = require('express');
const app = express();
const cors = require('cors');

app.use(cors({
  origin: "http://localhost:5173",
  methods: ["GET", "POST", "PUT", "DELETE"],
  credentials: true
}));

app.use(express.json());

const userRoute = require('./routes/UserRoute');
const vehicleRoute = require('./routes/VehicleRoute');
const partRoute = require('./routes/PartRoute');

app.use('/users', userRoute);
app.use('/vehicle', vehicleRoute);
app.use('/parts', partRoute);

app.listen(8080, () => {
    console.log('server works on port 8080');
})
