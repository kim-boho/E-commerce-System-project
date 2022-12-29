// @author Michelangelo Granato

import { useEffect, useMemo } from "react";
import { useNavigate } from "react-router-dom";
import Header from "./components/Header";
import Table from "./components/Table";
import { usePurchaseData } from "./hooks/usePurchaseData";
import { useIsAdmin } from "./hooks/userRequests";
import { useSiteUsageData } from "./hooks/useSiteUsage";

const Analytics = () => {
  // load site usage data
  const navigate = useNavigate();

  const eventData = useSiteUsageData().data;
  // TODO: load monthly purchases
  const purchaseData = usePurchaseData().data;
  const getUser = () => {
    return localStorage.getItem("user");
  };
  const { data: isAdmin } = useIsAdmin(localStorage.getItem("user")!);
  useEffect(() => {
    const getUser = () => {
      return localStorage.getItem("user");
    };

    if (getUser == null || isAdmin == false) {
      navigate("/login");
    }
  }, []);

  const siteUsageCols = useMemo(
    () => [
      {
        // first group - TV Show
        Header: "Site Usage",
        // First group columns
        columns: [
          {
            Header: "Date",
            accessor: "eventDate",
          },
          {
            Header: "IP Address",
            accessor: "ip",
          },
          {
            Header: "Product ID",
            accessor: "productId",
          },

          {
            Header: "Event Type",
            accessor: "eventType",
          },
        ],
      },
    ],
    []
  );
  const purchaseDataCols = useMemo(
    () => [
      {
        Header: "Monthly Purchases",
        columns: [
          {
            Header: "ID",
            accessor: "productId",
          },
          {
            Header: "Product Name",
            accessor: "productName",
          },
          {
            Header: "Category",
            accessor: "category",
          },
          {
            Header: "Event Type",
            accessor: "price",
          },
          {
            Header: "Number Sold",
            accessor: "quantity",
          },
        ],
      },
    ],
    []
  );
  return (
    <>
      <Header></Header>
      {eventData && <Table columns={siteUsageCols} data={eventData} />}
      {/*{purchaseData && (
        <Table columns={purchaseDataCols} data={purchaseData} />)*/}
    </>
  );
};

export default Analytics;
