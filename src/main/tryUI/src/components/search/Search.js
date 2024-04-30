import React, { useState, useCallback, useEffect } from "react";
import api from "../../api/axiosConfig";
import {
    AddressAutofill,
    useConfirmAddress,
    config,
} from "@mapbox/search-js-react";

export default function Search({ feature, setFeature, tries, setTries }) {
    const [showOverlay, setShowOverlay] = useState(false);
    const [showFormExpanded, setShowFormExpanded] = useState(false);
    const [showValidationText, setShowValidationText] = useState(false);
    const [token, setToken] = useState("");

    useEffect(() => {
        const accessToken =
            "";
        setToken(accessToken);
        config.accessToken = accessToken;
    }, []);

    const newTry = async (name) => {
        try {
            const response = await api.post(
                `/api/v1/tries/661f43c121e852e0fdc00e81?name=${name}&address=${feature.properties.place_name}&longitude=${feature.geometry.coordinates[0]}&latitude=${feature.geometry.coordinates[1]}`,
            );
            const updatedTries = [...tries, response.data];
            setTries(updatedTries);
        } catch (error) {
            console.log(error);
        }
    };

    const { formRef, showConfirm } = useConfirmAddress({
        minimap: true,
        skipConfirmModal: (feature) => {
            ["exact", "high"].includes(feature.properties.match_code.confidence);
        },
    });

    const handleRetrieve = (res) => {
        setFeature(res.features[0]);
        setShowFormExpanded(true);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        console.log();
        submitForm(e.currentTarget.elements.tryName.value);
    };

    function submitForm(name) {
        newTry(name);
        setShowValidationText(true);
        setTimeout(() => {
            resetForm();
        }, 2500);
    }

    function resetForm() {
        const inputs = document.querySelectorAll("input");
        inputs.forEach((input) => (input.value = ""));
        setShowFormExpanded(false);
        setShowValidationText(false);
        setShowOverlay(false);
        setFeature(null);
    }

    return (
        <>
            <div>
                <button
                    className="inline-flex w-full justify-center gap-x-1.5  bg-white px-3 py-2 text-sm font-semibold text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 hover:bg-gray-50"
                    onClick={() => setShowOverlay(true)}
                >
                    New Try
                </button>
            </div>
            <div
                className="fixed z-10 h-5/6 bg-white max-w-screen-sm"
                style={{ display: showOverlay ? "block" : "none" }}
            >
                <form
                    ref={formRef}
                    className="shadow-md pt-5 px-7 pb-7 mb-4"
                    onSubmit={handleSubmit}
                >
                    <div className="mb-4">
                        <label className="block text-gray-700 text-sm font-bold mb-2">
                            Address
                        </label>
                        <AddressAutofill accessToken={token} onRetrieve={handleRetrieve}>
                            <input
                                className="shadow appearance-none border w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                                placeholder="Start typing address, e.g. 123 Main..."
                                autoComplete="address-line1"
                                id="mapbox-autofill"
                            />
                        </AddressAutofill>
                    </div>
                    <div style={{ display: showFormExpanded ? "block" : "none" }}>
                        <div className="mb-4">
                            <label className="block text-gray-700 text-sm font-bold mb-2">
                                Name
                            </label>
                            <input
                                className="shadow appearance-none border w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                                placeholder="Try Name"
                                id="tryName"
                            />
                        </div>
                        <div className="mb-4">
                            <label className="block text-gray-700 text-sm font-bold mb-2">
                                Address Line 2
                            </label>
                            <input
                                className="shadow appearance-none border w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                                placeholder="Apartment, suite, unit, building, floor, etc."
                                autoComplete="address-line2"
                            />
                        </div>
                        <div className="mb-4">
                            <label className="block text-gray-700 text-sm font-bold mb-2">
                                City
                            </label>
                            <input
                                className="shadow appearance-none border  w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                                placeholder="City"
                                autoComplete="address-level2"
                            />
                        </div>
                        <div className="mb-4">
                            <label className="block text-gray-700 text-sm font-bold mb-2">
                                State / Region
                            </label>
                            <input
                                className="shadow appearance-none border  w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                                placeholder="State / Region"
                                autoComplete="address-level1"
                            />
                        </div>
                        <div className="mb-4">
                            <label className="block text-gray-700 text-sm font-bold mb-2">
                                ZIP / Postcode
                            </label>
                            <input
                                className="shadow appearance-none border  w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                                placeholder="ZIP / Postcode"
                                autoComplete="postal-code"
                            />
                        </div>
                    </div>

                    {showFormExpanded && (
                        <div className="flex items-center justify-between">
                            <button
                                type="submit"
                                className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4  focus:outline-none focus:shadow-outline"
                                id="btn-confirm"
                            >
                                Confirm
                            </button>
                            <button
                                type="button"
                                className="btn round btn--gray-light ml3"
                                id="btn-reset"
                                onClick={resetForm}
                            >
                                Cancel
                            </button>
                        </div>
                    )}
                </form>

                {showValidationText && (
                    <div id="validation-msg" className="mt24 txt-m txt-bold">
                        Order successfully submitted.
                    </div>
                )}
            </div>
        </>
    );
}
