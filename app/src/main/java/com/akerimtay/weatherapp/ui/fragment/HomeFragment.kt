package com.akerimtay.weatherapp.ui.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.akerimtay.weatherapp.R
import com.akerimtay.weatherapp.databinding.FragmentHomeBinding
import com.akerimtay.weatherapp.locationPermissionCode
import com.akerimtay.weatherapp.utils.*
import com.akerimtay.weatherapp.viewmodel.HomeViewModel
import com.google.android.gms.location.*
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefresh.setOnRefreshListener { updateUI(view) }

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                val lastLocation: Location = locationResult.lastLocation
                viewModel.getCurrentWeatherByLocation(lastLocation.latitude, lastLocation.longitude)
            }
        }

        getLastLocation()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            locationPermissionCode -> {
                if (verifyGrantResults(grantResults)) {
                    getLastLocation()
                } else {
                    if (!shouldShowRequestPermissionRationale(locationPermissions)) {
                        showDialogForEnablePermission(requireContext())
                    } else {
                        showSimpleToast(requireContext(), R.string.location_permission_not_granted)
                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            locationPermissionCode -> getLastLocation()
        }
    }

    private fun updateUI(view: View) {
        viewModel.currentWeather.value?.let {
            viewModel.getCurrentWeatherByLocation(it.location.latitude, it.location.longitude)
        } ?: showConnectionErrorToast(view.context)
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        if (isPermissionsGranted(requireContext(), locationPermissions)) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location ->
                    location?.let {
                        viewModel.getCurrentWeatherByLocation(it.latitude, it.longitude)
                    } ?: offGps()
                }
                .addOnFailureListener {
                    showSimpleToast(requireContext(), it.localizedMessage)
                }
        } else {
            requestPermissions(locationPermissions, locationPermissionCode)
        }
    }

    private fun offGps() {
        showSimpleToast(requireContext(), R.string.gps_disconnected)
        requestNewLocationData()
    }

    @SuppressLint("MissingPermission")
    private fun requestNewLocationData() {
        val locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 0
        locationRequest.fastestInterval = 0
        locationRequest.numUpdates = 1

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
    }

    private fun showDialogForEnablePermission(context: Context) {
        val dialog = AlertDialog.Builder(context, R.style.AppThemeAlertDialog)
            .setTitle(R.string.location_permission_rationale_title)
            .setMessage(R.string.settings_location_permission_rationale_body)
            .setNegativeButton(getString(R.string.not_now)) { dialog: DialogInterface, _: Int ->
                dialog.cancel()
                showSimpleToast(context, R.string.location_permission_not_granted)
            }
            .setPositiveButton(getString(R.string.go_to_settings)) { dialog: DialogInterface, _: Int ->
                dialog.cancel()
                startActivityForResult(getSettingsIntent(context), locationPermissionCode)
            }
        dialog.create().show()
    }
}