import { useEffect, useState } from "react";
import { useProductsData } from "../hooks/useProducts";

function Filter({ updateFilter }: any) {
  const products = useProductsData().data;
  const [brands, setBrands] = useState<string[]>([]);
  const [types, setTypes] = useState<string[]>([]);
  const [filter, setFilter] = useState<string[]>([]);

  useEffect(() => {
    const fetchAllBrand = () => {
      const res = products
        ?.map((item) => item.brand)
        .filter((value, index, self) => self.indexOf(value) === index);

      if (res) {
        setBrands(res);
      }
    };
    fetchAllBrand();
    const fetchAllTypes = () => {
      const res = products
        ?.map((item) => item.type)
        .filter((value, index, self) => self.indexOf(value) === index);

      if (res) {
        setTypes(res);
      }
    };
    fetchAllTypes();
  }, [products]);

  return (
    <div className="flex">
      <h2>Filter By: </h2>
      <select
        onChange={(e) => updateFilter(e)}
        className="bg-white h-10 mt-7 w-40 ml-5 border-2 border-black"
      >
        <option>--select brand--</option>
        {brands.map((brand: any) => {
          return <option>{brand}</option>;
        })}
      </select>

      <button className="bg-green-700 text-white w-36 h-30 mt-7">Search</button>
    </div>
  );
}

export default Filter;
