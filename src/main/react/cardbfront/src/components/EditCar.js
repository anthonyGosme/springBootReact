import React, { useState } from "react";

import Dialog from "@material-ui/core/Dialog";
import DialogActions from "@material-ui/core/DialogActions";
import DialogContent from "@material-ui/core/DialogContent";
import DialogTitle from "@material-ui/core/DialogTitle";
const EditCar = props => {
  const [open, setOpen] = useState(false);
  const [car, setCar] = useState({
    brand: "",
    model: "",
    color: "",
    year: "",
    fuel: "",
    price: ""
  });

  // Close the modal form
  const handleClose = () => {
    setOpen(false);
  };
  // get change
  const handleChange = e => {
    setCar({ ...car, [e.target.name]: e.target.value });
  };

  // open the modal form
  const handleClickOpen = () => {
    setCar({
      brand: props.car.brand,
      model: props.car.model,
      color: props.car.color,
      year: props.car.year,
      fuel: props.car.fuel,
      price: props.car.price
    });
    setOpen(true);
  };

  const updateCar = () => {
    props.updateCar(car, props.link);
    handleClose();
  };

  return (
    <div>
      <button
        style={{ margin: 10 }}
        variant="outlined"
        onClick={handleClickOpen}
      >
        Edit Car
      </button>

      <Dialog
        open={open}
        onClose={handleClose}
        aria-labelledby="form-dialog-title"
      >
        <DialogTitle>Edit Car</DialogTitle>
        <DialogContent>
          <input
            type="text"
            placeholder="Brand"
            name="brand"
            value={car.brand}
            onChange={handleChange}
          />
          <br />
          <input
            type="text"
            placeholder="Model"
            name="model"
            value={car.model}
            onChange={handleChange}
          />
          <br />
          <input
            type="text"
            placeholder="Color"
            name="color"
            value={car.color}
            onChange={handleChange}
          />
          <br />
          <input
            type="text"
            placeholder="Year"
            name="year"
            value={car.year}
            onChange={handleChange}
          />
          <br />
          <input
            type="text"
            placeholder="Price"
            name="price"
            value={car.price}
            onChange={handleChange}
          />
        </DialogContent>

        <DialogActions>
          <button onClick={handleClose} color="primary">
            cancel
          </button>
          <button onClick={updateCar} color="primary">
            save
          </button>
        </DialogActions>
      </Dialog>
    </div>
  );
};

export default EditCar;
